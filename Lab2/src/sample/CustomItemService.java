package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.json.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomItemService {
    List<String> updatedList = new ArrayList<>();


    public void ExecuteCommand(JSONArray finalAudit, ListView listView, ObservableList<String> observableList ) throws IOException, InterruptedException {
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < finalAudit.length(); i++) {
            map = finalAudit.getJSONObject(i).toMap();
            if (map.containsKey(" cmd ")) {
                String command = (String) map.get(" cmd ");
                command = command.replaceAll("\"", "");
                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.command("bash", "-c", command);
                Process process = processBuilder.start();

                StringBuilder output = new StringBuilder();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line + "\n");
                }

                int exitVal = process.waitFor();
                if (exitVal == 0) {
                    System.out.println(output);
                    String expectedResult = map.get(" expect ").toString();
                    expectedResult = expectedResult.replaceAll("\"", "");
                    Pattern pattern = Pattern.compile(expectedResult, Pattern.DOTALL);
                    Matcher m = pattern.matcher(output);
                    try {
                        if (m.find()) {

                            updatedList.add("Success! " + map.get(" description ").toString());

                        } else {
                            updatedList.add("--Skipped-- " + map.get(" description ").toString());

                        }
                    } catch (Exception e) {
                        updatedList.add("Failure! " + map.get(" description ").toString());

                    }
                }
            } else {
                System.out.println("Couldn't find command to execute");
            }
        }
        observableList = FXCollections.observableArrayList(updatedList);
        listView.setItems(observableList);

        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                        if (item != null && item.contains("Success!")) {
                            setStyle("-fx-control-inner-background: derive(#98fb98, 50%);");
                        } else if (item != null && item.contains("-Skipped-")) {
                            setStyle("-fx-control-inner-background: derive(yellow, 50%);");
                        } else if (item != null && item.contains("Failure!")) {
                            setStyle("-fx-control-inner-background: derive(red, 100%);");
                        }
                    }
                };
            }
        });
    }
}
