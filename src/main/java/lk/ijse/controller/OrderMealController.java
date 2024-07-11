package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.OrderMealBO;
import lk.ijse.bo.custom.ReservationBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.InventoryDetailsDTO;
import lk.ijse.dto.MealDetailsDTO;
import lk.ijse.dto.PlaceOrderMealDTO;
import lk.ijse.dto.tm.OrderMealCartDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMealController {

    @FXML
    public Label lableroomid, lableMinimumStockLevel, lableinventoryid, lablemealprice, lableIngredients, lableMealId, lableMealName, lableOrderDate, lablenextmealid;
    @FXML
    public JFXTextField txtqty;
    @FXML
    private JFXButton btnAddToCart, btnPlaceOrder, btnBack;
    @FXML
    private JFXComboBox<String> cmbMealname, cmbReservationId;
    @FXML
    private TableView<OrderMealCartDTO> tableordercart ;
    @FXML
    private TableColumn<OrderMealCartDTO, String>  colInventoryID, colMealID;

    @FXML
    private TableColumn<OrderMealCartDTO, Void> colaction;
    @FXML
    private TableColumn<OrderMealCartDTO, Double> colMealPrice,coltotalprice;
    @FXML
    private TableColumn<OrderMealCartDTO, Integer> colQuantity;

    @FXML
    public void initialize() {
        setupTableColumns();
        setReservationDate();
        populateReservationIDs();
        setupReservationIdChangeListener();
        loadMealNames();
        setupFocusTransferHandlers();
        Platform.runLater(() -> cmbReservationId.requestFocus());

    }

    OrderMealBO orderMealBO = (OrderMealBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDERMEAL);
    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);

    private void setupTableColumns() {
        colMealID.setCellValueFactory(new PropertyValueFactory<>("mealId"));
        colInventoryID.setCellValueFactory(new PropertyValueFactory<>("inventoryID"));
        colMealPrice.setCellValueFactory(new PropertyValueFactory<>("mealPrice"));
        coltotalprice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        setupRemoveButtonColumn();
    }

    private void setupRemoveButtonColumn() {
        colaction.setCellFactory(col -> new TableCell<OrderMealCartDTO, Void>() {
            private final Button btn = new Button("Remove");
            {
                btn.setOnAction(event -> {
                    OrderMealCartDTO item = getTableRow().getItem();
                    if (item != null) {
                        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this item?", ButtonType.YES, ButtonType.NO);
                        confirmationAlert.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.YES) {
                                getTableView().getItems().remove(item);
                            }
                        });
                    }
                });
                btn.setCursor(Cursor.HAND);
                setAlignment(Pos.CENTER);
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });
    }


    private void loadMealNames() {
        ObservableList<String> meals = FXCollections.observableArrayList();
        try {
            meals.addAll(orderMealBO.getAllMealNames());
            cmbMealname.setItems(meals);
            cmbMealname.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    loadMealDetails(newVal);
                }
            });
        } catch (SQLException e) {
            System.err.println("Error loading meal names: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadMealDetails(String mealName) {
        try {
            MealDetailsDTO mealDetails = orderMealBO.getMealIdByMealName(mealName);
            if (mealDetails != null) {
                lableMealId.setText(mealDetails.getMealId());
                lablemealprice.setText(String.format("%.2f", mealDetails.getPrice()));
                InventoryDetailsDTO inventoryDetails = orderMealBO.getInventoryDetailsByMealId(mealDetails.getMealId());
                if (inventoryDetails != null) {
                    lableinventoryid.setText(inventoryDetails.getInventoryID());
                    lableMinimumStockLevel.setText(String.valueOf(inventoryDetails.getMinimumStockLevel()));
                    lableIngredients.setText(inventoryDetails.getIngredients());
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load meal and inventory details: " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setReservationDate() {
        Thread dateTimeThread = new Thread(() -> {
            while (true) {
                LocalDate localDate = LocalDate.now();
                LocalTime localTime = LocalTime.now();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                Platform.runLater(() -> lableOrderDate.setText(dateFormatter.format(localDate) + " " + timeFormatter.format(localTime)));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        dateTimeThread.setDaemon(true);
        dateTimeThread.start();
    }

    private void populateReservationIDs() {
        ObservableList<String> reservationIDs = FXCollections.observableArrayList();
        try {
            List<String> ids = reservationBO.getDistinctReservationIds();
            reservationIDs.addAll(ids);
            cmbReservationId.setItems(reservationIDs);
        } catch (SQLException e) {
            System.err.println("Error fetching reservation IDs: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setupReservationIdChangeListener() {
        cmbReservationId.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                updateRoomIdsLabel(newVal);
            }
        });
    }

    private void updateRoomIdsLabel(String reservationId) {
        try {
            List<String> roomIds = reservationBO.getRoomIdsByReservationId(reservationId);
            lableroomid.setText(String.join(", ", roomIds));
        } catch (SQLException e) {
            System.err.println("Failed to fetch room IDs: " + e.getMessage());
            lableroomid.setText("Error loading rooms");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) this.btnAddToCart.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        // validate if all fields are filled
        if (cmbReservationId.getSelectionModel().isEmpty() || cmbMealname.getSelectionModel().isEmpty() || txtqty.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill all fields to add to cart").show();
            return;
        }


        String mealId = lableMealId.getText();
        String InventoryID = lableinventoryid.getText();
        double mealPrice = Double.parseDouble(lablemealprice.getText());
        int quantity = Integer.parseInt(txtqty.getText());
        double totalPrice = mealPrice * quantity;
        OrderMealCartDTO item = new OrderMealCartDTO(mealId,InventoryID, mealPrice, quantity, totalPrice);
        tableordercart.getItems().add(item);
    }

    @FXML
    public void cmbReservationIdOnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void cmbRoomIdOnAction(ActionEvent actionEvent) {

    }
    @FXML
    void handleRemoveAction(OrderMealCartDTO item) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this item?", ButtonType.YES, ButtonType.NO);
        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                tableordercart.getItems().remove(item);
            }
        });
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException {
        ObservableList<OrderMealCartDTO> items = tableordercart.getItems();
        if (items.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "No items in the cart to place the order.").show();
            return;
        }
        PlaceOrderMealDTO placeOrderMeal = new PlaceOrderMealDTO(items);
        boolean isUpdated = orderMealBO.placeOrderMeal(placeOrderMeal);
        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Order placed successfully").show();
            tableordercart.getItems().clear();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to place order").show();
        }
    }
    private void setupFocusTransferHandlers() {
        List<Control> controls = Arrays.asList(cmbReservationId, cmbMealname, txtqty, btnAddToCart, lableOrderDate);
        for (Control control : controls) {
            control.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    transferFocusAndOpen(control);
                }
            });
        }
    }

    private void transferFocusAndOpen(Control currentControl) {
        Control nextControl = null;
        switch (currentControl.getId()) {
            case "cmbReservationId":
                nextControl = cmbMealname;
                break;
            case "cmbMealname":
                nextControl = txtqty;
                break;
            case "txtqty":
                nextControl = btnAddToCart;
                break;
            default:
                break;
        }
        if (nextControl != null) {
            if (nextControl instanceof ComboBox) {
                ComboBox<?> nextCombo = (ComboBox<?>) nextControl;
                Platform.runLater(() -> {
                    nextCombo.requestFocus();
                    nextCombo.show();
                });
            } else if (nextControl instanceof Button) {
                Platform.runLater(nextControl::requestFocus);
            } else {
                Platform.runLater(nextControl::requestFocus);
            }
        }
    }
    @FXML
    public void TxtqtyOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.QTY,txtqty);
    }


    public void PrintBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/OrderMeal.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();
        data.put("MealID", lableMealId.getText());


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }
}
