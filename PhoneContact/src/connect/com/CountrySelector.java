package connect.com;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import API.API_Functions;
import API.CountryCode;

public class CountrySelector extends JTextField {
    private static final long serialVersionUID = 1L;

    private JPopupMenu suggestionsPopup;
    JList<String> suggestionList;
    private DefaultListModel<String> listModel;
    private List<CountryCode> countryList;
    private String code;

    public CountrySelector(String code) {
        countryList = API_Functions.loadFromJsonToCountryList("country_codes_data.json");
        String inputText="";
        this.code=code;
        int plusIndex = this.code.lastIndexOf("+");
        if (plusIndex != -1) {
            String dialCode = this.code.substring(plusIndex+1,this.code.length());
          
            for (CountryCode country : countryList) {
                if (dialCode.equals(country.getDial_code())) {
                	 inputText=  country.getName() + " (+" + country.getDial_code() + ")";
                	 
                }
            }
           
            CountrySelector.this.setText(inputText);
            
        }
        listModel = new DefaultListModel<>();
        suggestionList = new JList<>(listModel);
        suggestionsPopup = new JPopupMenu();

        JScrollPane scrollPane = new JScrollPane(suggestionList);
        scrollPane.setPreferredSize(new Dimension(200, 100));
        suggestionsPopup.add(scrollPane);
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
             int len=CountrySelector.this.getText().length();
             if (len>6) {
            	 CountrySelector.this.setText("");
             }
            }
        });
        

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = CountrySelector.this.getText().toLowerCase();
                listModel.clear();

                if (!text.isEmpty()) {
                    for (CountryCode country : countryList) {
                        if (country.getName().toLowerCase().startsWith(text)) {
                            listModel.addElement(country.getName() + " (+" + country.getDial_code() + ")");
                        }
                    }
                    if (!listModel.isEmpty()) {
                        suggestionsPopup.show(CountrySelector.this, 0, CountrySelector.this.getHeight());
                    } else {
                        suggestionsPopup.setVisible(false);
                    }
                } else {
                    suggestionsPopup.setVisible(false);
                }
            }
        });

     
    }

    public String getSelected(MouseEvent e) {
        int index = suggestionList.locationToIndex(e.getPoint());
        if (index >= 0) {
            String selectedValue = suggestionList.getModel().getElementAt(index);
            CountrySelector.this.setText(selectedValue);
            suggestionsPopup.setVisible(false);

            // Extract the dial code from the selected value (format: "CountryName (+Code)")
            int plusIndex = selectedValue.lastIndexOf("+");
            if (plusIndex != -1) {
                String dialCode = selectedValue.substring(plusIndex,selectedValue.length()-1);
                return dialCode;
            }
        }

        JOptionPane.showMessageDialog(null, "Please choose a dial code", "Error", JOptionPane.ERROR_MESSAGE);
        return null;
    }

}
