package Controller;

import Boundery.GUIPlayer;
import Entity.ChangeAppointList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * The Class AppChngEditingCell2 for cell font color - pick green for "Approve" and red for "Reject".
 */
class AppChngEditingCell2 extends TableCell<ChangeAppointList, String> {
	 
    /** The text field. */
    private TextField textField;
	
	/** The screen. */
	GUIPlayer screen = new GUIPlayer();
    


    /**
     * update cell item to green font for "Approve" or red font for "Reject"
     * @param item the item in the cell
     * @param empty check if the cell is empty
     */
    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(!isEmpty())
        {
        	if(item.contains("Approve"))
        		this.setTextFill(Color.GREEN);
        	else if(item.contains("Reject"))
            	this.setTextFill(Color.RED);
        }
        if (empty) {
            setText(null);
            setGraphic(null);
        
        } else {
        	
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }
            
    }

    /**
     * Creates the text field for cell.
     */
    @SuppressWarnings("unused")
	private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
        textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, 
                Boolean arg1, Boolean arg2) {
                    if (!arg2) {
                        commitEdit(textField.getText());
                    }
            }
        });
    }

    /**
     * Gets the string of the item in the cell.
     *
     * @return the string in the cell
     */
    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
