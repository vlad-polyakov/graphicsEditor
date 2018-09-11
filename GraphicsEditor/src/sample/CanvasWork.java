package sample;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class CanvasWork {
    private boolean lineBool;
    private boolean circleBool;
    private boolean rectBool;
    private boolean pencilBool;
    private boolean brushBool;
    private float brushSize;
    private boolean paintBool;
    private Canvas canvas;
    private MainWindow mainWindow;
    private Color lineColor;
    private double from_x = 0;
    private double from_y = 0;
    private double to_x = 0;
    private double to_y = 0;
    private int line_no = 1;

    public CanvasWork (MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }
    public Canvas addCanvas(){
        canvas = new Canvas(800,800);
        canvas.getGraphicsContext2D().setLineWidth(6);
        readClick();
        return canvas;
    }

    public void readClick(){
        if(pencilBool==true){
            lineColor = Color.GRAY;
        }
        if(brushBool==true){
            lineColor = Color.BLACK;
        }
        if(lineBool==true){
            initDraw(canvas.getGraphicsContext2D());
        }
        canvas.setOnMousePressed(MouseEvent -> {
            brushSize = mainWindow.sizeVariants.getValue();
            canvas.getGraphicsContext2D().setLineWidth(brushSize);
            canvas.getGraphicsContext2D().setStroke(lineColor);
            if(lineBool==true) {
                //System.out.println("0");
                this.setFromPos(MouseEvent);
            }
            if(paintBool == true){
                canvas.getGraphicsContext2D().beginPath();
                canvas.getGraphicsContext2D().moveTo(MouseEvent.getX(), MouseEvent.getY());
                canvas.getGraphicsContext2D().stroke();
            }
            //}
        });
        canvas.setOnMouseDragged(MouseEvent->
        {
            if(paintBool == true) {
                canvas.getGraphicsContext2D().lineTo(MouseEvent.getX(), MouseEvent.getY());
                canvas.getGraphicsContext2D().stroke();
            }
            if(lineBool==true){
                mainWindow.canvasAndTools.getChildren().remove(0);
                final Canvas temp_canvas = new Canvas(800, 800);
                final GraphicsContext gc = temp_canvas.getGraphicsContext2D();
                this.setToPos(MouseEvent);
                this.drawLine(gc);
                mainWindow.canvasAndTools.getChildren().add(0,temp_canvas);
            }
        });  canvas.setOnMouseReleased((event)->{
            if (lineBool == true) {
                final Canvas new_line = new Canvas(800, 800);
                final GraphicsContext gc = new_line.getGraphicsContext2D();
                this.setToPos(event);
                this.drawLine(gc);
                //final new stright lin
                mainWindow.canvasAndTools.getChildren().add(line_no++, new_line);
                canvas = new_line;

            }
        });
    }
    public void paintMode(){
        this.paintBool = true;
        this.lineBool = false;
        this.circleBool = false;
        this.rectBool = false;
    }
    public void lineMode(){
        this.lineBool = true;
        this.paintBool = false;
        this.circleBool = false;
        this.rectBool = false;
    }
    public void pencilMode(){this.pencilBool = true;
    this.brushBool = false;}
    public void brushMode(){this.pencilBool = false;
        this.brushBool = true;}
    public void circleMode(){
        this.circleBool = true;
        this.lineBool = false;
    }
    public void setLineColor(Color color){
        this.lineColor = color;
    }

    /*private void setFromPos(MouseEvent event) {
        this.from_x = event.getSceneX();
        this.from_y = event.getSceneY();
    }

    private void setToPos(MouseEvent event) {
        this.to_x = event.getSceneX();
        this.to_y = event.getSceneY();
    }
*/
    private void setFromPos(MouseEvent event) {
        this.from_x = event.getX();
        this.from_y = event.getY();
    }

    private void setToPos(MouseEvent event) {
        this.to_x = event.getX();
        this.to_y = event.getY();
    }
    private void drawLine(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(brushSize);
        gc.strokeLine(from_x, from_y, to_x, to_y);
    }

    private void initDraw(GraphicsContext gc){
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();
        gc.setFill(lineColor);
        gc.setStroke(lineColor);
        gc.setLineWidth(brushSize);

        gc.fill();
        /*gc.strokeRect(
                0,              //x of the upper left corner
                0,              //y of the upper left corner
                canvasWidth,    //width of the rectangle
                canvasHeight);  //height of the rectangle*/
    }

}
