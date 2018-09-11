package sample;

public class Controller {
    private CanvasWork canvasWork;
    public Controller (CanvasWork canvasWork){
        this.canvasWork = canvasWork;
    }
    public void createLine(){
        canvasWork.lineMode();
        canvasWork.readClick();
    }
    public void pencil(){
        canvasWork.pencilMode();
        canvasWork.readClick();
    }

    public void brush(){
        canvasWork.brushMode();
        canvasWork.readClick();
    }
    public void createCircle(){
        canvasWork.circleMode();
        canvasWork.readClick();
    }

    public void createSomething(){
        canvasWork.paintMode();
        canvasWork.readClick();
    }
}
