package MPSTreeFileIO;

import MPSdataStructures.Node;
import MPSdataStructures.Tree;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TreeFile {
    public void writeTreeToFile(Tree tree){
        String jsonTree = new JSONObject()
                .put("nrNodes", tree.getNrNodes())
                .put("thresholds", tree.getThresholds())
                .put("root", writeNodeToFile(tree.getRoot()))
                .toString();

        try (FileWriter fw = new FileWriter("bestTreeJSON.txt")){
            fw.write(jsonTree);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String writeNodeToFile(Node node){
        JSONObject jsonNode =  new JSONObject()
                .put("op", node.getOp())
                .put("val", node.getVal());

        if(node.getLeft() != null){
            jsonNode.put("left", writeNodeToFile(node.getLeft()));
        }

        if(node.getRight() != null){
            jsonNode.put("right", writeNodeToFile(node.getRight()));
        }

        return jsonNode.toString();
    }

    public Tree readTreeFromFile(){
        String jsonString = null;

        try(Scanner sc= new Scanner(new File("bestTreeJSON.txt"))){
            jsonString = sc.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (jsonString == null) {
            return null;
        }

        JSONObject treeJson = new JSONObject(jsonString);

        return parseTreeFromJSON(treeJson);
    }

    public Node parseNodeFromJSON(JSONObject nodeJson){
        Node node = new Node(nodeJson.getDouble("val"));

        if (nodeJson.has("op")) {
            node.setOp(nodeJson.getString("op"));
        }

        else node.setOp(null);


        if(nodeJson.has("left")){
            node.setLeft(parseNodeFromJSON(new JSONObject(nodeJson.getString("left"))));
        }

        else node.setLeft(null);


        if(nodeJson.has("right")){
            node.setRight(parseNodeFromJSON(new JSONObject(nodeJson.getString("right"))));
        }

        else node.setRight(null);

        return node;
    }
    public Tree parseTreeFromJSON(JSONObject treeJson){
        if(treeJson == null){
            return null;
        }

        ArrayList<Double> thresholds = new ArrayList<>();
        JSONArray thresholdsJson = treeJson.getJSONArray("thresholds");

        for(int i = 0; i < thresholdsJson.length(); i++) {
            thresholds.add(thresholdsJson.getDouble(i));
        }

        return new Tree(
                parseNodeFromJSON(new JSONObject(treeJson.getString("root"))),
                treeJson.getInt("nrNodes"),
                thresholds
        );
    }
}
