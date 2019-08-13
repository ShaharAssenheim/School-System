package Controller;

import java.io.IOException;

import Boundery.GUIPlayer;
import Entity.ChangeAppointList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * The Class AppChngEditingCell for event when click on current cell.
 */
class AppChngEditingCell extends TableCell<ChangeAppointList, String> {
	 
    /** The text field. */
    private TextField textField;
	
	/** The screen. */
	GUIPlayer screen = new GUIPlayer();
	
	/** The list. */
	ObservableList<ChangeAppointList> list;
    
    /**
     * Instantiates a new AppChngEditingCell.
     *
     * @param list the list of requests
     */
    public AppChngEditingCell(ObservableList<ChangeAppointList> list) {
    	this.list = list;
    	this.setTextFill(Color.BLUE);
    }

    /**
     * event when click on this cell
     */
    @Override
    public void startEdit() {
        if (!isEmpty()) {
        	try {
        		for(int i=0;i<list.size();i++)
        		{
        		if(getItem().equals((list.get(i).getRequestCol())))
        				{
							screen.SchDir_TeaApp(list.get(i));
			        		break;
        				}
        		}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }


    /**
     * update cell item
     * @param item the item in the cell
     * @param empty check if the cell is empty
     */
    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

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
