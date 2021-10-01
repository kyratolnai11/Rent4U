package Client;

import Client.Core.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class Rent4UApp extends Application
{
    @Override
    public void start(Stage stage) throws Exception {
        ViewHandler.getInstance().start();
    }
}
