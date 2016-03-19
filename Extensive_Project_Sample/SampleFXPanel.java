import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import misc.GUIConstants;
import misc.Parameters;
import misc.components.fx.*;
import misc.components.fx.EFXButton.IconType;
import ap.server.Pomast;
import ap.server.RemoteChangePoId;
import ap.server.RemotePomast;
import main.client.Client;
import main.client.Special;
import main.server.RemoteApParmtr;

/**
 * Sample Java FX Class
 */

public final class SampleFXPanel extends EFXLockHolderPanel implements Runnable{

  // Non-gui variables
  private RemoteApParmtr apParmtr = null;
  private HashMap apParmtrHash = null;
  private McsSpecial parentFrame = null;
  private Timeline timer = null;
  private RemoteChangePoId changePoId = null;
  private RemotePomast oldPomast = null;

  private EFXButton closeButton = new EFXButton(GUIConstants.FX_CLOSE, IconType.CANCEL);
  private EFXButton nextButton = new EFXButton(GUIConstants.FX_NEXT, IconType.NEXT);

  // Labels
  private EFXLabel backupLabel = new EFXLabel("There should be a current backup of the data before proceeding!", false);
  private EFXLabel oldPoIdLabel = new EFXLabel("Old P.O. Id:");
  private EFXLabel newPoIdLabel = new EFXLabel("New P.O. Id:");
  private EFXLabel vnmIdLabel = new EFXLabel("Vendor Id:");
  private EFXLabel vnmNameLabel = new EFXLabel("Vendor Name:");
  private EFXLabel poitemLabel = new EFXLabel("Poitem:");
  private EFXLabel pomastLabel = new EFXLabel("Pomast:");
  private EFXLabel poitemAcctsLabel = new EFXLabel("PoitemAccts:");
  private EFXLabel rqmastLabel = new EFXLabel("Rqmast:");
  private EFXLabel voidChktransLabel = new EFXLabel("VoidChktrans:");
  private EFXLabel gltransLabel = new EFXLabel("Gltrans:");
  private EFXLabel batransLabel = new EFXLabel("Batrans:");
  private EFXLabel prempbamLabel = new EFXLabel("Prempbam:");
  private EFXLabel chkbatchLabel = new EFXLabel("Chkbatch:");
  private EFXLabel prbambatchLabel = new EFXLabel("Prbambatch:");
  private EFXLabel icSerialNumLabel = new EFXLabel("IcSerialNum:");
  private EFXLabel ictransLabel = new EFXLabel("Ictrans:");
  private EFXLabel potransLabel = new EFXLabel("Potrans:");
  private EFXLabel fnchgtrnLabel = new EFXLabel("Fnchgtrn:");
  private EFXLabel attachmentsLabel = new EFXLabel("Attachments:");

  private EFXUpperMaskField oldPoIdField = new EFXUpperMaskField(8);
  private EFXUpperMaskField newPoIdField = new EFXUpperMaskField(8);
  private EFXUpperMaskField vnmIdField = new EFXUpperMaskField(8);
  private EFXTextField vnmNameField = new EFXTextField(30);

  private EFXNumericField poitemField = new EFXNumericField(13);
  private EFXNumericField pomastField = new EFXNumericField(13);
  private EFXNumericField voidChktransField = new EFXNumericField(13);
  private EFXNumericField gltransField = new EFXNumericField(13);
  private EFXNumericField batransField = new EFXNumericField(13);
  private EFXNumericField prempbamField = new EFXNumericField(13);
  private EFXNumericField poitemAcctsField = new EFXNumericField(13);
  private EFXNumericField rqmastField = new EFXNumericField(13);
  private EFXNumericField chkbatchField = new EFXNumericField(13);
  private EFXNumericField prbambatchField = new EFXNumericField(13);
  private EFXNumericField icSerialNumField = new EFXNumericField(13);
  private EFXNumericField ictransField = new EFXNumericField(13);
  private EFXNumericField potransField = new EFXNumericField(13);
  private EFXNumericField fnchgtrnField = new EFXNumericField(13);
  private EFXNumericField attachmentsField = new EFXNumericField(13);

  // ********************* Constructors ***********************//

  public SampleFXPanel(McsSpecial h) throws RemoteException {
    super(h);
    parentFrame = h;
  }

  @Override
  public void init() {
    try {
      apParmtr = parentFrame.getServer().getApParmtr("1");
      apParmtrHash = apParmtr.getAllFields();

      setToolbarButtons(nextButton, closeButton);
      setPanelContent(createCenterPanel());
      enableFields();
    }
    catch(Exception ex) {
      parentFrame.displayErrorMessage(ex, getLocalLockHolder());
    }
  }

  void enableFields() {
    oldPoIdField.setDisable(false);
    newPoIdField.setDisable(false);
  }

  @Override
  public void setEventHandlers() {
    oldPoIdField.setOnAction(action -> oldPoIdFieldActionPerformed());
    newPoIdField.setOnAction(action -> newPoIdFieldActionPerformed());

    nextButton.setOnAction(action -> nextButtonActionPerformed());
    closeButton.setOnAction(action -> closeButtonActionPerformed());
  }

  VBox createCenterPanel() throws RemoteException {
    changePoId = parentFrame.getServer().getChangePoId();
    backupLabel.setRed();

    EFXGridPane grid = new EFXGridPane();
    grid.addColumn(HPos.RIGHT, 0, oldPoIdLabel, vnmIdLabel, vnmNameLabel, new EFXLabel(""), pomastLabel, poitemLabel, poitemAcctsLabel,
        potransLabel, chkbatchLabel, attachmentsLabel);
    grid.addColumn(HPos.LEFT, 1, oldPoIdField, vnmIdField, vnmNameField, new EFXLabel(""), pomastField, poitemField, poitemAcctsField,
        potransField, chkbatchField, attachmentsField);
    grid.addColumn(HPos.RIGHT, 2, new EFXLabel(""), new EFXLabel(""), new EFXLabel(""), new EFXLabel(""), batransLabel, gltransLabel,
        voidChktransLabel, fnchgtrnLabel, Parameters.reqstn_installed == 'Y' ? rqmastLabel : null);
    grid.addColumn(HPos.LEFT, 3, newPoIdLabel, new EFXLabel(""), new EFXLabel(""), new EFXLabel(""), batransField, gltransField,
        voidChktransField, fnchgtrnField, Parameters.reqstn_installed == 'Y' ? rqmastField : null);
    grid.addColumn(HPos.RIGHT, 4, newPoIdField, new EFXLabel(""), new EFXLabel(""), new EFXLabel(""), Parameters.payroll_installed == 'J'
        || Parameters.payroll_installed == 'A' ? prempbamLabel : null, Parameters.payroll_installed == 'J'
        || Parameters.payroll_installed == 'A' ? prbambatchLabel : null, Parameters.inv_ctrl_installed == 'Y' ? icSerialNumLabel : null,
        Parameters.inv_ctrl_installed == 'Y' ? ictransLabel : null);
    grid.addColumn(HPos.LEFT, 5, new EFXLabel(""), new EFXLabel(""), new EFXLabel(""), new EFXLabel(""),
        Parameters.payroll_installed == 'J' || Parameters.payroll_installed == 'A' ? prempbamField : null,
        Parameters.payroll_installed == 'J' || Parameters.payroll_installed == 'A' ? prbambatchField : null,
        Parameters.inv_ctrl_installed == 'Y' ? icSerialNumField : null, Parameters.inv_ctrl_installed == 'Y' ? ictransField : null);

    EFXGridPane.setColumnSpan(vnmNameField, 3);
    EFXGridPane.setHalignment(newPoIdLabel, HPos.RIGHT);
    EFXGridPane.setHalignment(newPoIdField, HPos.LEFT);

    return wrapV(Pos.TOP_LEFT, 5, new Insets(10), backupLabel, grid);
  }

  // ********************* Accessor Methods ***************************//

  @Override
  public Node getFocusComponent() {
    return oldPoIdField;
  }

  @Override
  public EFXButton getCloseButton() {
    return closeButton;
  }

  boolean areRequiredFieldsOK() {
    String message = toContinueMessage;
    boolean requiredFieldsCompleted = true;
    try {
      if(!parentFrame.getServer().isRecordValid(Pomast.class.getName(), oldPoIdField.getValue())) {
        message = message + "Old P.O. \n";
        requiredFieldsCompleted = false;
        vnmIdField.setValue("");
        vnmNameField.setValue("");
      }
      else {
        oldPomast = parentFrame.getServer().getPomast(oldPoIdField.getValue(), false);
        if(oldPomast.getViatech_Flag() == 'Y') {
          message = message + "Viatech P.O.'s can not be changed\n";
          requiredFieldsCompleted = false;
          vnmIdField.setValue("");
          vnmNameField.setValue("");
        }
        else {
          vnmIdField.setValue(oldPomast.getVnm_Id());
          vnmNameField.setValue(oldPomast.getVnm_Name());
        }
      }
      if(newPoIdField.length() > 0) {
        if(parentFrame.getServer().isRecordValid(Pomast.class.getName(), newPoIdField.getValue())) {
          message = message + "The New P.O. already exists\n";
          requiredFieldsCompleted = false;
        }
        else {
          if(Parameters.city_id.equals("1300") && Parameters.state.equals("NJ")) {
            if(newPoIdField.getValue().substring(0, ((String)apParmtrHash.get(Parameters.AUTONUM_PO_CYPREFIX)).length())
                .equals((apParmtrHash.get(Parameters.AUTONUM_PO_CYPREFIX)))) {
              // new po id begins with current prefix - this is ok - do nothing
            }
            else {
              if(((String)apParmtrHash.get(Parameters.AUTONUM_PO_FYPREFIX)).length() > 0
                  && newPoIdField.getValue().substring(0, ((String)apParmtrHash.get(Parameters.AUTONUM_PO_FYPREFIX)).length())
                      .equals((apParmtrHash.get(Parameters.AUTONUM_PO_FYPREFIX)))) {
                // new po id begins with future prefix - this is ok - do nothing
              }
              else {
                if(!Character.isDigit(newPoIdField.charAt(0))
                    || !Character.isDigit(newPoIdField.charAt(1)) || !Character.isDigit(newPoIdField.charAt(2))) {
                  // new po id has at least one non-numeric char - this is ok - do nothing
                }
                else {
                  if(((String)apParmtrHash.get(Parameters.AUTONUM_PO_FYPREFIX)).length() > 0) {
                    message = message
                        + "One of the autonumber prefixes must be used for non-Viatech P.O.'s or\n"
                        + "at least one character in the first three positions must be non-numeric";
                  }
                  else {
                    message = message
                        + "The autonumber prefix '"
                        + ((String)apParmtrHash.get(Parameters.AUTONUM_PO_CYPREFIX))
                        + "' must be used for non-Viatech P.O.'s or\n at least one character in the first three positions must be non-numeric";
                  }
                  requiredFieldsCompleted = false;
                }
              }
            }
          }
        }
      }
      else {
        message = message + "New P.O.\n";
        requiredFieldsCompleted = false;
      }
      if(!requiredFieldsCompleted) {
        new EFXAlert(getScene().getWindow(), message).showAndWait();
      }
      repaint();
      return requiredFieldsCompleted;
    }
    catch(Exception ex) {
      parentFrame.displayErrorMessage(ex, getLocalLockHolder());
      return false;
    }
  }

  // ******************* Action Methods *********************//

  void oldPoIdFieldActionPerformed() {
    try {
      if(!parentFrame.getServer().isRecordValid(Pomast.class.getName(), oldPoIdField.getValue())) {
        new EFXAlert(getScene().getWindow(), getInvalidFieldMessage("P.O.", true)).showAndWait();
        oldPoIdField.setValid(false);
        vnmIdField.setValue("");
        vnmNameField.setValue("");
      }
      else {
        oldPomast = parentFrame.getServer().getPomast(oldPoIdField.getValue(), false);
        if(oldPomast.getViatech_Flag() == 'Y') {
          new EFXAlert(getScene().getWindow(), "Viatech P.O.'s can not be changed").showAndWait();
          oldPoIdField.setValid(false);
          vnmIdField.setValue("");
          vnmNameField.setValue("");
        }
        else {
          vnmIdField.setValue(oldPomast.getVnm_Id());
          vnmNameField.setValue(oldPomast.getVnm_Name());
        }
      }
    }
    catch(Exception ex) {
      parentFrame.displayErrorMessage(ex, getLocalLockHolder());
    }
  }

  void newPoIdFieldActionPerformed() {
    try {
      if(newPoIdField.length() > 0) {
        if(parentFrame.getServer().isRecordValid(Pomast.class.getName(), newPoIdField.getValue())) {
          new EFXAlert(getScene().getWindow(), "That P.O. already exists.").showAndWait();
          newPoIdField.setValid(false);
        }
        else {
          if(Parameters.city_id.equals("1300") && Parameters.state.equals("NJ")) {
            if(newPoIdField.getValue().substring(0, ((String)apParmtrHash.get(Parameters.AUTONUM_PO_CYPREFIX)).length())
                .equals((apParmtrHash.get(Parameters.AUTONUM_PO_CYPREFIX)))) {
              // new po id begins with current prefix - this is ok - do nothing
            }
            else {
              if(((String)apParmtrHash.get(Parameters.AUTONUM_PO_FYPREFIX)).length() > 0
                  && newPoIdField.getValue().substring(0, ((String)apParmtrHash.get(Parameters.AUTONUM_PO_FYPREFIX)).length())
                      .equals((apParmtrHash.get(Parameters.AUTONUM_PO_FYPREFIX)))) {
                // new po id begins with future prefix - this is ok - do nothing
              }
              else {
                if(!Character.isDigit(newPoIdField.charAt(0))
                    || !Character.isDigit(newPoIdField.charAt(1)) || !Character.isDigit(newPoIdField.charAt(2))) {
                  // new po id has at least one non-numeric char - this is ok - do nothing
                }
                else {
                  if(((String)apParmtrHash.get(Parameters.AUTONUM_PO_FYPREFIX)).length() > 0) {
                    new EFXAlert(getScene().getWindow(), "One of the autonumber prefixes must be used for non-Viatech P.O.'s or\n"
                        + "at least one character in the first three positions must be non-numeric").showAndWait();
                  }
                  else {
                    new EFXAlert(
                        getScene().getWindow(),
                        "The autonumber prefix '"
                            + ((String)apParmtrHash.get(Parameters.AUTONUM_PO_CYPREFIX))
                            + "' must be used for non-Viatech P.O.'s or\n at least one character in the first three positions must be non-numeric")
                        .showAndWait();
                  }
                  newPoIdField.setValid(false);
                }
              }
            }
          }
        }
      }
      else {
        new EFXAlert(getScene().getWindow(), getInvalidFieldMessage("P.O.", true)).showAndWait();
        newPoIdField.setValid(false);
      }
    }
    catch(Exception ex) {
      parentFrame.displayErrorMessage(ex, getLocalLockHolder());
    }
  }

  void closeButtonActionPerformed() {
    this.getFrame().close();
  }

  void nextButtonActionPerformed() {
    boolean personnel = false;
    String message = "";
    String message1 = "";
    if(areRequiredFieldsOK()) {
      try {
        if(Parameters.payroll_installed == 'J' || Parameters.payroll_installed == 'A' || Parameters.hr_installed == 'Y') {
          personnel = parentFrame.getServer().isPersonnelModuleLocked();
          message = "There are users in the Finance or Personnel module; please lock them out and close open screens:\n";
          message1 = "The Finance & Personnel Modules must be locked to other users and all other screens must be closed before beginning.";
        }
        else {
          personnel = true;
          message = "There are users in the Finance module; please lock them out and close open screens:\n";
          message1 = "The Finance Module must be locked to other users and all other screens must be closed before beginning.";
        }

        ArrayList list = null;
        if(Parameters.payroll_installed == 'J' || Parameters.payroll_installed == 'A' || Parameters.hr_installed == 'Y') {
          list = parentFrame.getServer().getOpenFrameTitles('F', 'P');
        }
        else {
          list = parentFrame.getServer().getOpenFrameTitles('F');
        }
        if(list.size() == 0 || list.size() == 1) {
          if(parentFrame.getServer().isFinanceModuleLocked() && personnel) {
            if(parentFrame.checkOpenPanels()) {
              Optional<ButtonType> result = new EFXAlert(AlertType.WARNING, getScene().getWindow(), "Select an Option",
                  "WARNING:  Are you sure you want to run the Change P.O. Number Routine?", ButtonType.YES, ButtonType.NO).showAndWait();
              if(result.get() == ButtonType.YES) {
                Thread updateThread = new Thread(this);
                updateThread.start();
              }
            }
          }
          else {
            new EFXAlert(getScene().getWindow(), message1).showAndWait();
          }
        }
        else {
          for(int i = 0; i < list.size(); i++) {
            message = message + list.get(i) + "\n";
          }
          new EFXAlert(getScene().getWindow(), message).showAndWait();
        }
      }
      catch(Exception ex) {
        parentFrame.displayErrorMessage(ex, getLocalLockHolder());
      }
    }
  }

  @Override
  public void run() {
    try {
      Platform.runLater(() -> {
        poitemField.setValue("");
        pomastField.setValue("");
        voidChktransField.setValue("");
        gltransField.setValue("");
        batransField.setValue("");
        potransField.setValue("");
        fnchgtrnField.setValue("");
        attachmentsField.setValue("");
        poitemAcctsField.setValue("");
          if(Parameters.reqstn_installed == 'Y') {
            rqmastField.setValue("");
          }
          chkbatchField.setValue("");
          if(Parameters.payroll_installed == 'J' || Parameters.payroll_installed == 'A') {
            prempbamField.setValue("");
            prbambatchField.setValue("");
          }
          if(Parameters.inv_ctrl_installed == 'Y') {
            icSerialNumField.setValue("");
            ictransField.setValue("");
          }

          repaint();
          validate();
          nextButton.setDisable(true);
          closeButton.setDisable(true);
          setStatusBarText("Running the routine.");
        });
      timer = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
      timer.setCycleCount(Animation.INDEFINITE);
      timer.play();
      changePoId.changePoId(oldPoIdField.getValue(), newPoIdField.getValue(), getLocalLockHolder(), McsClient.userId);
      timer.stop();
      Platform.runLater(() -> {
        closeButton.setDisable(false);
        nextButton.setDisable(false);
        setStatusBarText("");

        // refresh the counter fields one more time to make sure they are up to date
          HashMap hash = null;
          try {
            hash = changePoId.getUpdateCount();
          }
          catch(Exception ex) {
            parentFrame.displayErrorMessage(ex);
          }
          poitemField.setValue((String)hash.get("Poitem"));
          pomastField.setValue((String)hash.get("Pomast"));
          voidChktransField.setValue((String)hash.get("VoidChktrans"));
          gltransField.setValue((String)hash.get("Gltrans"));
          batransField.setValue((String)hash.get("Batrans"));
          potransField.setValue((String)hash.get("Potrans"));
          fnchgtrnField.setValue((String)hash.get("Fnchgtrn"));
          attachmentsField.setValue((String)hash.get("Attachments"));
          poitemAcctsField.setValue((String)hash.get("PoitemAccts"));
          if(Parameters.reqstn_installed == 'Y') {
            rqmastField.setValue((String)hash.get("Rqmast"));
          }
          chkbatchField.setValue((String)hash.get("Chkbatch"));
          if(Parameters.payroll_installed == 'J' || Parameters.payroll_installed == 'A') {
            prbambatchField.setValue((String)hash.get("Prbambatch"));
            prempbamField.setValue((String)hash.get("Prempbam"));
          }
          if(Parameters.inv_ctrl_installed == 'Y') {
            icSerialNumField.setValue((String)hash.get("IcSerialNum"));
            ictransField.setValue((String)hash.get("Ictrans"));
          }

          repaint();
          validate();
        });
    }
    catch(Exception ex) {
      parentFrame.displayErrorMessage(ex, getLocalLockHolder());
    }
  }

  private EventHandler<ActionEvent> eventHandler = event -> {
    try {
      HashMap hash = null;
      hash = changePoId.getUpdateCount();
      poitemField.setValue((String)hash.get("Poitem"));
      pomastField.setValue((String)hash.get("Pomast"));
      voidChktransField.setValue((String)hash.get("VoidChktrans"));
      gltransField.setValue((String)hash.get("Gltrans"));
      batransField.setValue((String)hash.get("Batrans"));
      potransField.setValue((String)hash.get("Potrans"));
      fnchgtrnField.setValue((String)hash.get("Fnchgtrn"));
      attachmentsField.setValue((String)hash.get("Attachments"));
      poitemAcctsField.setValue((String)hash.get("PoitemAccts"));
      if(Parameters.reqstn_installed == 'Y') {
        rqmastField.setValue((String)hash.get("Rqmast"));
      }
      chkbatchField.setValue((String)hash.get("Chkbatch"));
      if(Parameters.payroll_installed == 'J' || Parameters.payroll_installed == 'A') {
        prbambatchField.setValue((String)hash.get("Prbambatch"));
        prempbamField.setValue((String)hash.get("Prempbam"));
      }
      if(Parameters.inv_ctrl_installed == 'Y') {
        icSerialNumField.setValue((String)hash.get("IcSerialNum"));
        ictransField.setValue((String)hash.get("Ictrans"));
      }

      repaint();
      validate();
    }
    catch(Exception ex) {
      parentFrame.displayErrorMessage(ex);
    }
  };
}
