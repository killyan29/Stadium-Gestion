package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DatabaseConnection;
import model.Terrain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainController {

    @FXML
    private TableView<Terrain> terrainTable;
    @FXML
    private TableColumn<Terrain, String> colNom;
    @FXML
    private TableColumn<Terrain, String> colStatut;

    public void initialize() {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));

        terrainTable.setItems(getTerrains());
    }

    private ObservableList<Terrain> getTerrains() {
        ObservableList<Terrain> terrains = FXCollections.observableArrayList();
        Connection conn = DatabaseConnection.getConnection();

        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM terrains");

                while (rs.next()) {
                    terrains.add(new Terrain(rs.getString("nom"), rs.getString("statut")));
                }

                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return terrains;
    }
}