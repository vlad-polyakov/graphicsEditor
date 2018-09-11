package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class MainWindow {
    private CanvasWork canvasWork;
    private Controller controller;
    public ComboBox<Integer>sizeVariants;
    private ObservableList<Integer> comboBoxList = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
    public HBox canvasAndTools;
    public VBox createScene(){
        canvasWork = new CanvasWork(this);
        controller = new Controller(canvasWork);
        VBox mainLayout = new VBox();
        HBox buttonsLayout = new HBox();
        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.RED);
        canvasWork.setLineColor(colorPicker.getValue());
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                canvasWork.setLineColor(colorPicker.getValue());
            }
        });
        buttonsLayout.setPadding(new Insets(10));
        Button paintBut = new Button("Paint");
        paintBut.setOnAction(event -> controller.createSomething());
        Button lineBut = new Button("Line");
        lineBut.setOnAction(event -> controller.createLine());
        Button rectangleBut = new Button("Rectangle");
        Button circleBut = new Button("Circle");
        circleBut.setOnAction(event -> controller.createCircle());
        Button pencilBut = new Button("Pencil");
        pencilBut.setOnAction(event -> controller.pencil());
        Button brushBut = new Button("Brush");
        brushBut.setOnAction(event -> controller.brush());
        sizeVariants = new ComboBox(comboBoxList);
        sizeVariants.setValue(6);
        MenuBar menuList = new MenuBar();
        Menu fileWorkMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        MenuItem newFileItem = new Menu("New");
        MenuItem loadFileItem = new Menu("Load");
        fileWorkMenu.getItems().addAll(newFileItem,loadFileItem);
        menuList.getMenus().addAll(fileWorkMenu,editMenu);
        canvasAndTools = new HBox();
        VBox tools = new VBox();
        tools.getChildren().addAll(pencilBut,brushBut,sizeVariants);
        canvasAndTools.getChildren().addAll(canvasWork.addCanvas(),tools);
        buttonsLayout.getChildren().addAll(paintBut,lineBut,circleBut,rectangleBut,colorPicker);
        mainLayout.getChildren().addAll(menuList,buttonsLayout,canvasAndTools);

        return mainLayout;
    }
}
