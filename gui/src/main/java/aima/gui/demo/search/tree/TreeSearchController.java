package aima.gui.demo.search.tree;

import aima.gui.demo.search.problem.rectangular.RectangularGridProblemController;
import aima.gui.demo.search.problem.rectangular.AtVertex;
import aima.gui.demo.search.tree.algorithm.GeneralTreeSearchController;
import aima.gui.demo.search.tree.algorithm.TreeSearchAlgoSimulator;
import aima.gui.demo.search.tree.info.SummaryInfoController;
import aima.gui.demo.search.tree.info.SearchSpaceInfoController;
import aima.gui.support.fx.FXUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * @author Ciaran O'Reilly
 */
public class TreeSearchController {
    @FXML private AnchorPane problemPane;
    @FXML private AnchorPane searchAlgoPane;
    @FXML private AnchorPane stateSpaceInfo;
    @FXML private AnchorPane searchSpaceInfo;
    @FXML private AnchorPane frontierInfo;
    //
    private TreeSearchAlgoSimulator<AtVertex> simulator = new TreeSearchAlgoSimulator<>();

    @FXML
    private void initialize() throws IOException {
        FXMLLoader problemLoader = new FXMLLoader(RectangularGridProblemController.class.getResource("rectangulargridproblem.fxml"));
        Pane problem = problemLoader.load();
        FXUtil.anchor(problem);
        problemPane.getChildren().addAll(problem);
        RectangularGridProblemController problemController = problemLoader.getController();

        Pane state = problemController.createSearchSpaceInfoRepresentation();
        FXUtil.anchor(state);
        stateSpaceInfo.getChildren().add(state);

        problemController.setSimulator(simulator);

        FXMLLoader treeSearchLoader = new FXMLLoader(GeneralTreeSearchController.class.getResource("generaltreesearch.fxml"));
        Pane algo = treeSearchLoader.load();
        FXUtil.anchor(algo);
        searchAlgoPane.getChildren().add(algo);
        TreeSearchAlgoSimulator.Observer<AtVertex> treeSearchController = treeSearchLoader.getController();
        treeSearchController.setSimulator(simulator);

        Pane search = FXMLLoader.load(SearchSpaceInfoController.class.getResource("searchspaceinfo.fxml"));
        FXUtil.anchor(search);
        searchSpaceInfo.getChildren().add(search);

        FXMLLoader frontierInfoLoader = new FXMLLoader(SummaryInfoController.class.getResource("summaryInfo.fxml"));
        Pane frontierInfoPane = frontierInfoLoader.load();
        FXUtil.anchor(frontierInfoPane);
        frontierInfo.getChildren().add(frontierInfoPane);
        TreeSearchAlgoSimulator.Observer<AtVertex> frontierInfoController = frontierInfoLoader.getController();
        frontierInfoController.setSimulator(simulator);
    }
}
