package sample;

import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;


public class TableManager<S> {
    TableView table;

    public TableManager(TableView<S> table) { this.table = table; }

    public void setupColumns(){
        ObservableList<TableColumn> columns = table.getColumns();
        for (int i = 0; i < table.getColumns().size(); i++){
            columns.get(i).setCellValueFactory(new PropertyValueFactory<>(columns.get(i).getId()));
        }
    }

    public void addItem(S item) {
        table.getItems().add(item);
    }

    public void addItems(ArrayList<S> items) {
        table.getItems().addAll(items);
    }

    public ObservableList<S> getItems() {
        return table.getItems();
    }

    public void removeItem(S item) {
        table.getItems().remove(item);
    }

    public void removeItem(int index) {
        table.getItems().remove(index);
    }

    public void clearItems() {
        table.getItems().clear();
    }

    public void refresh() {
        ObservableList<TableColumn> columns = table.getColumns();
        for (int i = 0; i < table.getColumns().size(); i++){
            columns.get(i).setVisible(false);
            columns.get(i).setVisible(true);
        }
    }

    public void clearSelection() {
        table.getSelectionModel().clearSelection();
    }

    public void setMultiSelectEnabled(boolean enabled) {
        if (enabled)
            table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        else
            table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
