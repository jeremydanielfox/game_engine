package gae.gameView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import shop.CursorBinder;
import engine.gameobject.Editable;
import gae.backend.TempTower;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;


// import gameobject.Editable;

public class LibraryView {
    // public void editEditableObject(Editable e);

    public static final int THUMBNAIL_SIZE=20;
    private Pane libraryView=new Pane();
    private Map<String, ObservableList<Editable>> map;
    private Stage stage;

    public LibraryView (Stage st, Map<String, ObservableList<Editable>> librarymap) {
        stage = st;
        map = librarymap;
        libraryView.getChildren().add(makeAccordion());
    }
    
    public Pane initialize(){
        return libraryView;
    }

    private Accordion makeAccordion () {
        Accordion accordion = new Accordion();
        for (String key : map.keySet()) {
            TitledPane title = new TitledPane(key, createList(map.get(key)));
            accordion.getPanes().add(title);
        }
        return accordion;
    }

    private Node createList (ObservableList<Editable> editables) {
        ListView<TempTower> list = new ListView<>();
        
        //to be changed to editable in real implementation
        List<TempTower> towerlist=new ArrayList<>();
        towerlist.add(new TempTower("yo"));
        towerlist.add(new TempTower("mama"));
        
        
      //  list.setItems(editableToLibraryContent(FXCollections.observableList(towerlist)));
        list.setItems(FXCollections.observableList(towerlist));
        list.setCellFactory(new Callback<ListView<TempTower>, ListCell<TempTower>>(){           
            @Override
            public ListCell<TempTower> call(ListView<TempTower> p) {                 
                ListCell<TempTower> cell = new ListCell<TempTower>(){ 
                    @Override
                    protected void updateItem(TempTower edit, boolean bln) {
                        super.updateItem(edit, bln);
                        if (edit != null) {
                            setGraphic(createCellContent(edit));
                        }
                    }
                };               
                return cell;
            }
        });   
        setListClick(list);
        return list;
    }

//    private ObservableList<LibraryContent> editableToLibraryContent(ObservableList<TempTower> editables){
//        ObservableList<LibraryContent> list=FXCollections.observableList(new ArrayList<>());
//        for(TempTower edit: editables){
//            LibraryContent content=new LibraryContent(edit.getImage(), edit.getName());
//            content.setEditable(edit);
//            list.add(content);
//        }
//        return list;
//    }
    
    private void setListClick (ListView<TempTower> list) {
        list.setOnMouseClicked(e->{
            TempTower currentItem=list.getSelectionModel().getSelectedItem();
            ImageView image=new ImageView(new Image(getClass().getResourceAsStream(currentItem.getImage())));
            Node binder=CursorBinder.bindCursor(image,
                                   stage.getScene(),
                                   KeyCode.ESCAPE);
            binder.setOnMouseClicked(ev -> {
                Circle xx=new Circle();
                xx.setRadius(50.0f);
              xx.setCenterX(ev.getSceneX());
              xx.setCenterY(ev.getSceneY());
              libraryView.getChildren().add(xx);
              });
        libraryView.getChildren().add(binder);
        });
        
    }

    private Node createCellContent(TempTower edit){
        HBox content=new HBox();
        ImageView image=new ImageView(new Image(getClass().getResourceAsStream(edit.getImage())));
        image.setFitHeight(THUMBNAIL_SIZE);
        image.setPreserveRatio(true);
        content.getChildren().addAll(image, new Label(edit.getName()));
        return content;
    }

//    private class LibraryContent {
//
//        private HBox content;
//        private ImageView graphicIcon;
//        private String label;
//        private Editable editable;
//
//        // this means that all editables must have image and name
//        public LibraryContent (ImageView image, String objectName) {
//            content = new HBox(15);
//            graphicIcon=image;
//            label = objectName;
//            content.getChildren().add(graphicIcon);
//            content.getChildren().add(new Label(label));
//            setUpClick();
//        }
//        
//        private void setEditable(Editable edit){
//            editable=edit;
//        }
//        
//        private Node getContent(){
//            return content;
//        }
//
//        private void setUpClick () {
//            // TODO Auto-generated method stub
//
//        }
//
//    }
}
