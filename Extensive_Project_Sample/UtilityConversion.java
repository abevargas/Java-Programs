import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import misc.Parameters;
import misc.day.Day;

public class UtilityConversion extends CommonUtilityConversion {

  private BufferedReader reader = null;
  private String fileLine = "";
  HashMap<String, String> utmIdHashMap = new HashMap<String, String>();
  HashMap<String, String> altIdCycleMap = new HashMap<String, String>();
  HashMap<String, String> numDialsMap = new HashMap<String, String>();
  HashMap<String, String[]> dupBillsMap = new HashMap<String, String[]>();

  // Meter Info Vars
  private char meterServiceType = 'W';
  private int meterSeq = 1;
  private char meterStatus = 'A';
  private String meterStatusReason = "";
  private Day meterStatusDate = Day.EMPTY_DAY;
  private char meterType = ' ';
  private char meterGroup = '1';
  private String meterNum = "";
  private String meterSerialNum = "";
  private String meterRadioNum = "";
  private String meterLocation = "";
  private String meterBook = "";
  private String meterPage = "";
  private int meterNumDials = 4;
  private int meterMult = 0;
  private int meterMult2 = 0;
  private char meterGunType = ' ';
  private char meterGunRdgType = 'R';
  private String meterReadResol = "";
  private String meterBadgerMVRS = "";
  private String meterDescript = "";
  private BigDecimal meterPipeSize = Parameters.ZERO;
  private String meterPipeFoot = "";
  private Day meterInstallDate = Day.EMPTY_DAY;
  private String meterMiscCode = "";
  private int meterReplacedSeq = 0;
  private BigDecimal meterLatitude = Parameters.ZERO;
  private BigDecimal meterLongitude = Parameters.ZERO;

  public UtilityConversion() {
    super();
  }

  public static void main(String[] args) {
    try {
      UtilityConversion conversion = new UtilityConversion();
      conversion.runConversion(args);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  protected String getTargetVersion() {
    return VERSION51;
  }

  boolean doCommit() {
    return true;
  }

  public HashMap<String, Object> parseFileLine(String line) throws Exception {
    HashMap<String, Object> fields = new HashMap<String, Object>();
    ArrayList tokens = Parameters.readCSVRecord2(line);
    fields.put("AcctId", tokens.get(0));
    fields.put("ServLoc", tokens.get(1));
    fields.put("BlockSub", tokens.get(2));
    fields.put("SewerCode", tokens.get(3));
    fields.put("SewerUnits", tokens.get(4));
    fields.put("SewerFixturesNum", tokens.get(5));
    fields.put("OwnerName", tokens.get(6));
    fields.put("OwnerName2", tokens.get(7));
    fields.put("OwnerAddr1", tokens.get(8));
    fields.put("OwnerAddr2", tokens.get(9));
    fields.put("OwnerAddr3", tokens.get(10));
    fields.put("OwnerPhone", tokens.get(11));
    fields.put("LotSub", tokens.get(12));
    fields.put("Qual", tokens.get(13));
    fields.put("TenantName", tokens.get(14));
    fields.put("TenantName2", tokens.get(15));
    fields.put("TenantAddr1", tokens.get(16));
    fields.put("TenantAddr2", tokens.get(17));
    fields.put("TenantAddr3", tokens.get(18));
    fields.put("TenantPhone", tokens.get(19));
    fields.put("CarrierRt", tokens.get(20));
    fields.put("Cycle", tokens.get(21));
    fields.put("DevCode", tokens.get(22));
    fields.put("WalkRoute", tokens.get(23));
    fields.put("ConnectAcct", tokens.get(24));
    fields.put("AcctType", tokens.get(27));
    fields.put("MailGroup", tokens.get(28));
    fields.put("WaterCode", tokens.get(29));
    fields.put("WaterUnits", tokens.get(30));
    fields.put("FireCode", tokens.get(31));
    fields.put("FireUnits", tokens.get(32));
    fields.put("Hydrants", tokens.get(34));
    return fields;
  }

  public HashMap<String, Object> parseNotes(String line) throws Exception {
    HashMap<String, Object> fields = new HashMap<String, Object>();
    ArrayList tokens = Parameters.readCSVRecord2(line);
    fields.put("AcctId", tokens.get(0));
    fields.put("Note", tokens.get(1));
    return fields;
  }

  public HashMap<String, Object> parseMetersFile(String line) throws Exception {
    HashMap<String, Object> fields = new HashMap<String, Object>();
    ArrayList tokens = Parameters.readCSVRecord2(line);
    fields.put("AcctId", tokens.get(0));
    fields.put("Book", tokens.get(1));
    fields.put("Page", tokens.get(2));
    fields.put("Serial", tokens.get(3));
    fields.put("MeterNo", tokens.get(4));
    fields.put("MeterMake", tokens.get(5));
    fields.put("MeterType", tokens.get(6));
    fields.put("Size", tokens.get(8));
    fields.put("Multiplier", tokens.get(9));
    fields.put("LocationCode", tokens.get(10));
    fields.put("LocationMessage", tokens.get(11));
    fields.put("InstallDate", tokens.get(12));
    fields.put("ServiceNote", tokens.get(13));
    fields.put("Note1", tokens.get(14));
    fields.put("Note2", tokens.get(15));
    fields.put("Note3", tokens.get(16));
    fields.put("Active", tokens.get(17));
    return fields;
  }

  public HashMap<String, Object> parseMeterReadsFile(String line) throws Exception {
    HashMap<String, Object> fields = new HashMap<String, Object>();
    ArrayList tokens = Parameters.readCSVRecord2(line);
    fields.put("AcctId", tokens.get(0));
    fields.put("Date", tokens.get(1));
    fields.put("Reading", tokens.get(2));
    fields.put("Usage", tokens.get(3));
    fields.put("Type", tokens.get(4));
    return fields;
  }

  public HashMap<String, Object> parseTransHistFile(String line) throws Exception {
    HashMap<String, Object> fields = new HashMap<String, Object>();
    ArrayList tokens = Parameters.readCSVRecord2(line);
    fields.put("AcctId", tokens.get(0));
    fields.put("BillCode", tokens.get(1));
    fields.put("TransType", tokens.get(2));
    fields.put("PostDate", tokens.get(3));
    fields.put("TransDate", tokens.get(4));
    fields.put("Amount", tokens.get(5));
    fields.put("Balance", tokens.get(6));
    return fields;
  }

  public void parseDuplicateBills(String line) throws Exception {
    HashMap<String, Object> fields = new HashMap<String, Object>();
    ArrayList tokens = Parameters.readCSVRecord2(line);
    String acctId = (String)tokens.get(0).toString().trim();
    String addr = (String)tokens.get(1).toString().trim();
    String name = (String)tokens.get(2).toString().trim();
    String active = (String)tokens.get(3).toString().trim();
    String[] value = {addr, name, active};
    dupBillsMap.put(acctId, value);
  }

  public void parseMeterDialsFile(String line) throws Exception {
    HashMap<String, Object> fields = new HashMap<String, Object>();
    ArrayList tokens = Parameters.readCSVRecord2(line);
    String meterNum = (String)tokens.get(0).toString().trim();
    String dials = (String)tokens.get(1).toString().trim();
    numDialsMap.put(meterNum, dials);
  }

  public HashMap<String, Object> parseBPReSeqFile(String line) throws Exception {
    HashMap<String, Object> fields = new HashMap<String, Object>();
    ArrayList tokens = Parameters.readCSVRecord2(line);
    fields.put("UtmId", tokens.get(0));
    fields.put("Status", tokens.get(1));
    fields.put("Book", tokens.get(2));
    fields.put("Page", tokens.get(3));
    fields.put("SerialNum", tokens.get(4));
    fields.put("MeterNum", tokens.get(5));
    fields.put("NewPage", tokens.get(6));
    return fields;
  }

  protected void processFiles() throws Exception {
    try {
      statement.execute("DELETE FROM Utmast");
      statement.execute("DELETE FROM UtmastBillCodes");
      statement.execute("DELETE FROM Utmeters");
      statement.execute("DELETE FROM UtmastNote");
      statement.execute("DELETE FROM Utbillcode");
      statement.execute("DELETE FROM Utcycle");
      statement.execute("DELETE FROM UtWSreadings");
      statement.execute("DELETE FROM UtOtrreadings");
      statement.execute("DELETE FROM UtElcreadings");
      statement.execute("DELETE FROM UtSwrbilling");
      statement.execute("DELETE FROM UtWtrbilling");
      statement.execute("DELETE FROM UtElcbilling");
      statement.execute("DELETE FROM UtOtrbilling");
      statement.execute("DELETE FROM UtWtrpayments");
      statement.execute("DELETE FROM UtSwrpayments");
      statement.execute("DELETE FROM UtElcpayments");
      statement.execute("DELETE FROM UtOtrpayments");
      statement.execute("DELETE FROM UtOverpayments");
      statement.execute("DELETE FROM Pwpaycode");
      statement.execute("DELETE FROM Utadjcode");
      statement.execute("DELETE FROM Utadjbatch");
      statement.execute("DELETE FROM Batchid");
      statement.execute("DELETE FROM Utlocation");

      // Duplicate Bills
      reader = new BufferedReader(new FileReader("Utility\\CSV Data\\DuplicateBillsTenants.csv"));
      fileLine = reader.readLine();
      fileLine = reader.readLine();

      while(fileLine != null) {
        if(fileLine.trim().length() == 0) {
          fileLine = reader.readLine();
          continue;
        }
        parseDuplicateBills(fileLine);
        fileLine = reader.readLine();
      }

      // Get Meter Dials
      reader = new BufferedReader(new FileReader("Utility\\CSV Data\\MeterDialSize.csv"));
      fileLine = reader.readLine();
      fileLine = reader.readLine();

      while(fileLine != null) {
        if(fileLine.trim().length() == 0) {
          fileLine = reader.readLine();
          continue;
        }
        parseMeterDialsFile(fileLine);
        fileLine = reader.readLine();
      }

      // Customer Master
      reader = new BufferedReader(new FileReader("Utility\\CSV Data\\Master.csv"));
      fileLine = reader.readLine();
      fileLine = reader.readLine();
      String billCode = "";
      String acctId = "";
      int acctNum = 1;
      int noteSeq = 1;
      while(fileLine != null) {
        if(fileLine.trim().length() == 0) {
          fileLine = reader.readLine();
          continue;
        }
        String parcel = "";
        String block = "";
        String subB = "";
        String lot = "";
        String subL = "";
        String qual = "";
        BigDecimal sUnits = Parameters.ONE;
        BigDecimal wUnits = Parameters.ONE;
        BigDecimal fUnits = Parameters.ONE;

        HashMap fields = parseFileLine(fileLine);

        if(fields.containsKey("AcctId")) {
          acctId = (String)fields.get("AcctId").toString().replace("\"", "").trim();

          if(utmIdHashMap.containsKey(acctId)) {
            System.out.println("Duplicate acct: " + acctId);
            fileLine = reader.readLine();
            continue;
          }

          old_utm_acct = acctId;
          alternate_id = old_utm_acct;
          utm_acct = acctNum;
          sub_num = 0;
          utm_id = formatUtmId(utm_acct, sub_num);
          location_id = justify(acctNum + "", 8, 'R');
          otr_cycle = "  2";

          utmIdHashMap.put(acctId, utm_id);
          acctNum++;
        }

        if(fields.containsKey("BlockSub")) {
          parcel = (String)fields.get("BlockSub").toString().replace("\"", "").trim();

          if(parcel.contains(".")) {
            block = parcel.substring(0, parcel.indexOf("."));
            subB = parcel.substring(parcel.indexOf(".") + 1);
          }
          else if(Integer.parseInt(parcel) != 0)
            block = parcel;
        }

        if(fields.containsKey("LotSub")) {
          parcel = (String)fields.get("LotSub").toString().replace("\"", "").trim();

          if(parcel.contains(".")) {
            lot = parcel.substring(0, parcel.indexOf("."));
            subL = parcel.substring(parcel.indexOf(".") + 1);
          }
          else if(Integer.parseInt(parcel) != 0)
            lot = parcel;
        }

        if(fields.containsKey("Qual")) {
          qual = (String)fields.get("Qual").toString().replace("\"", "").trim();
        }

        parcel = getBlqId(block, subB, lot, subL, qual);
        blq_id = parcel;

        if(fields.containsKey("Cycle")) {
          String cycle = (String)fields.get("Cycle").toString().replace("\"", "").trim();
          switch(cycle) {
            case "A":
              swr_active_sw = "A";
              wtr_active_sw = "A";
              swr_cycle = "  1";
              wtr_cycle = "  1";
              break;
            case "B":
              swr_active_sw = "A";
              wtr_active_sw = "A";
              swr_cycle = "  2";
              wtr_cycle = "  2";
              break;
            case "C":
              swr_active_sw = "A";
              wtr_active_sw = "A";
              swr_cycle = "  3";
              wtr_cycle = "  3";
              break;
            case "F":
              otr_active_sw = "A";
              break;
            case "L":
              swr_active_sw = "A";
              wtr_active_sw = "A";
              swr_cycle = "  4";
              wtr_cycle = "  4";
              break;
            case "S":
              swr_active_sw = "A";
              wtr_active_sw = "A";
              swr_cycle = "  4";
              wtr_cycle = "  4";
              break;
            default:
              swr_active_sw = "I";
              wtr_active_sw = "I";
              swr_cycle = "  1";
              wtr_cycle = "  1";
              break;
          }
          altIdCycleMap.put(acctId, cycle);

          if(cycle.equals("F"))
            if(fields.containsKey("Hydrants")) {
              String temp = (String)fields.get("Hydrants").toString().replace("\"", "").trim();
              BigDecimal numHydrants = new BigDecimal(temp);
              if(numHydrants.compareTo(Parameters.ZERO) > 0)
                insertUtmastBillCode(utm_id, 'O', getNextUtmastBillCodeSeq(utm_id, 'O'), "H01", numHydrants, ' ', 'Y');
            }
        }

        if(fields.containsKey("OwnerName")) {
          owner_name = (String)fields.get("OwnerName").toString().replace("\"", "").trim();
        }

        if(fields.containsKey("OwnerAddr1")) {
          owner_street1 = (String)fields.get("OwnerAddr1").toString().replace("\"", "").trim();
        }

        if(fields.containsKey("OwnerAddr2")) {
          owner_city_st = (String)fields.get("OwnerAddr2").toString().replace("\"", "").trim();
        }

        if(fields.containsKey("OwnerAddr3")) {
          String add3 = (String)fields.get("OwnerAddr3").toString().replace("\"", "").trim();
          if(!add3.isEmpty()) {
            owner_street2 = owner_city_st;
            owner_city_st = add3;
          }
        }

        if(!owner_city_st.isEmpty()) {
          owner_zip = removeInvalidCharsNumsOnly(getCityStZip(owner_city_st)[1]);
          owner_city_st = getCityStZip(owner_city_st)[0];
        }

        if(fields.containsKey("OwnerName2")) {
          String temp = (String)fields.get("OwnerName2").toString().replace("\"", "").trim();
          if(!temp.isEmpty())
            if((owner_street1 + " " + owner_street2).trim().length() <= 30) {
              owner_street2 = owner_street1 + " " + owner_street2;
              owner_street1 = temp;
            }
          else
            System.out.println(alternate_id + " - Owner Name 2: \"" + temp + "\"");
        }

        if(fields.containsKey("OwnerPhone")) {
          owner_phone = removeInvalidCharsNumsOnly((String)fields.get("OwnerPhone").toString().replace("\"", "").trim());
          if(!owner_phone.isEmpty()) {
            if(owner_phone.charAt(0) == '0')
              owner_phone = "";
            else if(owner_phone.length() == 7)
              owner_phone = "   " + owner_phone;
          }
        }

        if(fields.containsKey("TenantName")) {
          name = (String)fields.get("TenantName").toString().replace("\"", "").trim();
        }

        if(fields.containsKey("TenantAddr1")) {
          street1 = (String)fields.get("TenantAddr1").toString().replace("\"", "").trim();
        }

        if(fields.containsKey("TenantAddr2")) {
          city_st = (String)fields.get("TenantAddr2").toString().replace("\"", "").trim();
        }

        if(fields.containsKey("TenantAddr3")) {
          String add3 = (String)fields.get("TenantAddr3").toString().replace("\"", "").trim();
          if(!add3.isEmpty()) {
            street2 = city_st;
            city_st = (String)fields.get("TenantAddr3").toString().replace("\"", "").trim();
          }
        }

        if(!city_st.isEmpty()) {
          zip = removeInvalidCharsNumsOnly(getCityStZip(city_st)[1]);
          city_st = getCityStZip(city_st)[0];
        }

        if(fields.containsKey("TenantName2")) {
          String temp = (String)fields.get("TenantName2").toString().replace("\"", "").trim();
          if(!temp.isEmpty())
            if((street1 + " " + street2).trim().length() <= 30) {
              street2 = street1 + " " + street2;
              street1 = temp;
            }
          else
            System.out.println(alternate_id + " - Tenant Name 2: \"" + temp + "\"");
        }

        if(fields.containsKey("TenantPhone")) {
          phone = removeInvalidCharsNumsOnly((String)fields.get("TenantPhone").toString().replace("\"", "").trim());
          if(!phone.isEmpty()) {
            if(phone.charAt(0) == '0')
              phone = "";
            else if(phone.length() == 7)
              phone = "   " + phone;
          }
        }

        if(fields.containsKey("ConnectAcct")) {
          String temp = (String)fields.get("ConnectAcct").toString().replace("\"", "").trim();
          if(temp.equals("N")) {
            wtr_active_sw = "I";
            swr_active_sw = "I";
            otr_active_sw = "I";
          }
        }

        if(fields.containsKey("ServLoc")) {
          service_loc = (String)fields.get("ServLoc").toString().replace("\"", "").trim();
          property_loc = service_loc;
        }

        if(fields.containsKey("SewerUnits")) {
          sUnits = new BigDecimal((String)fields.get("SewerUnits").toString().replace("\"", "").trim());
          if(sUnits.compareTo(Parameters.ZERO) == 0)
            sUnits = Parameters.ONE;
        }

        if(fields.containsKey("WaterUnits")) {
          wUnits = new BigDecimal((String)fields.get("WaterUnits").toString().replace("\"", "").trim());
          if(wUnits.compareTo(Parameters.ZERO) == 0)
            wUnits = Parameters.ONE;
        }

        if(fields.containsKey("FireUnits")) {
          fUnits = new BigDecimal((String)fields.get("FireUnits").toString().replace("\"", "").trim());
          if(fUnits.compareTo(Parameters.ZERO) == 0)
            fUnits = Parameters.ONE;
        }

        if(fields.containsKey("AcctType")) {
          String tempType = (String)fields.get("AcctType").toString().replace("\"", "").trim();

          switch(tempType) {
            case "APARTMENTS":
              acct_type = "APT";
              break;
            case "COMMERCIAL":
              acct_type = "COM";
              break;
            case "INDUSTRIAL":
              acct_type = "IND";
              break;
            case "RESIDENTIAL":
              acct_type = "RES";
              break;
            case "FIRE SERVIC":
              acct_type = "FIR";
              break;
            case "LAWN SPRINK":
              acct_type = "LWN";
              break;
            case "SWIM CLUB":
              acct_type = "SWM";
              break;
            default:
              acct_type = "RES";
              break;
          }
        }

        if(fields.containsKey("SewerCode")) {
          billCode = "S" + (String)fields.get("SewerCode").toString().replace("\"", "").trim();
          if(!billCode.contains("00") && !billCode.contains("99")) {
            insertUtmastBillCode(utm_id, 'S', getNextUtmastBillCodeSeq(utm_id, 'S'), billCode, sUnits, '1', 'Y');
            if(billCode.equals("S52") || billCode.equals("S56"))
              swr_ded_code = "DIS";
          }
          else
            swr_active_sw = "I";
        }

        if(fields.containsKey("WaterCode")) {
          billCode = "W" + (String)fields.get("WaterCode").toString().replace("\"", "").trim();
          if(!billCode.contains("00"))
            insertUtmastBillCode(utm_id, 'W', getNextUtmastBillCodeSeq(utm_id, 'W'), billCode, wUnits, '1', 'Y');
          else
            wtr_active_sw = "I";
        }

        if(fields.containsKey("FireCode")) {
          billCode = "F" + (String)fields.get("FireCode").toString().replace("\"", "").trim();
          if(!billCode.contains("00"))
            insertUtmastBillCode(utm_id, 'O', getNextUtmastBillCodeSeq(utm_id, 'O'), billCode, fUnits, ' ', 'Y');
          else
            otr_active_sw = "I";
        }

        if(fields.containsKey("CarrierRt")) {
          carrier_route = (String)fields.get("CarrierRt").toString().replace("\"", "").trim();
        }

        if(dupBillsMap.containsKey(acctId)) {
          co_notify_3rd_flag = "B";
          co_appl_street1 = dupBillsMap.get(acctId)[0].replace("\"", "");
          co_appl_city_st = city_st;
          co_appl_zip = zip;
          co_appl_name = dupBillsMap.get(acctId)[1].replace("\"", "");
        }

        insertUtmast();
        insertUtlocation(location_id);
        initializeUtmastVariables();

        fileLine = reader.readLine();
      }
      System.out.println("Completed Master");

      // Insert Notes
      reader = new BufferedReader(new FileReader("Utility\\CSV Data\\Notes.csv"));
      fileLine = reader.readLine();
      fileLine = reader.readLine();

      while(fileLine != null) {
        if(fileLine.trim().length() == 0) {
          fileLine = reader.readLine();
          continue;
        }
        HashMap fields = parseNotes(fileLine);

        if(fields.containsKey("AcctId")) {
          acctId = (String)fields.get("AcctId").toString().replace("\"", "").trim();

          if(utmIdHashMap.get(acctId) == null) {
            System.out.println("Null Note Id: " + acctId);
            fileLine = reader.readLine();
            continue;
          }
          else
            utm_id = utmIdHashMap.get(acctId);
          ;
        }
        if(fields.containsKey("Note")) {
          String note = (String)fields.get("Note").toString().replace("\"", "").trim();
          if(!note.isEmpty()) {
            updateNotesExistFlag();
            insertUtmastNote(utm_id, 1, note);
          }
        }
        fileLine = reader.readLine();
      }
      System.out.println("Inserted Notes");

      // Trans History
      reader = new BufferedReader(new FileReader("Utility\\CSV Data\\CustHistReport.csv"));
      fileLine = reader.readLine();
      fileLine = reader.readLine();
      Day transDate = Day.EMPTY_DAY;
      Day postDate = Day.EMPTY_DAY;
      BigDecimal amount = Parameters.ZERO;
      String transType = "";
      char service = ' ';

      while(fileLine != null) {
        if(fileLine.trim().length() == 0) {
          fileLine = reader.readLine();
          continue;
        }
        HashMap fields = parseTransHistFile(fileLine);

        if(fields.containsKey("PostDate")) {
          postDate = getDate((String)fields.get("PostDate").toString().replace("\"", "").trim());
          if(postDate.getYear() < 2013) {
            fileLine = reader.readLine();
            continue;
          }
          year = postDate.getYearString();
        }

        if(fields.containsKey("AcctId")) {
          acctId = (String)fields.get("AcctId").toString().replace("\"", "").trim();
          utm_id = utmIdHashMap.get(acctId);
        }

        if(fields.containsKey("BillCode")) {
          billCode = (String)fields.get("BillCode").toString().replace("\"", "").trim();
          if(billCode.startsWith("S"))
            service = 'S';
          else if(billCode.startsWith("W"))
            service = 'W';
          else if(billCode.startsWith("F"))
            service = 'O';
        }

        if(fields.containsKey("TransType")) {
          transType = (String)fields.get("TransType").toString().replace("\"", "").trim();
        }

        if(fields.containsKey("TransDate")) {
          transDate = getDate((String)fields.get("TransDate").toString().replace("\"", "").trim());
        }

        if(fields.containsKey("Amount")) {
          String temp = (String)fields.get("Amount").toString().replace("\"", "").replace(",", "").trim();

          // Check if amount is negative ex. 98-
          if(temp.charAt(temp.length() - 1) == '-')
            temp = "-" + temp.substring(0, temp.length() - 1);

          amount = new BigDecimal(temp);
        }
        int transPrd = 4;
        int month = postDate.getMonth();
        String tCycle = altIdCycleMap.get(acctId);
        if(tCycle.equals("B") || tCycle.equals("F")) { // Cycle B
          if(month > 1 && month < 5)
            transPrd = 1;
          else if(month > 4 && month < 8)
            transPrd = 2;
          else if(month > 7 && month < 11)
            transPrd = 3;
          else if(month > 10 || month == 1)
            transPrd = 4;
          if(month == 1) {
            year = postDate.getYear() - 1 + "";
          }
        }
        else if(tCycle.equals("C")) { // Cycle C
          if(month > 2 && month < 6)
            transPrd = 1;
          else if(month > 5 && month < 9)
            transPrd = 2;
          else if(month > 8 && month < 12)
            transPrd = 3;
          else if(month > 11 || month < 3)
            transPrd = 4;
          if(month < 3) {
            year = postDate.getYear() - 1 + "";
          }
        }
        else if(tCycle.equals("S") || tCycle.equals("L"))
          transPrd = 1;
        else {
          // Cycle A
          if(month < 4)
            transPrd = 1;
          else if(month < 7)
            transPrd = 2;
          else if(month < 10)
            transPrd = 3;
          else if(month >= 10)
            transPrd = 4;
          if(!tCycle.equals("A"))
            System.out.println("Invalid Cycle: " + altIdCycleMap.get(acctId));
        }
        if(year.equals("2012")) {
          fileLine = reader.readLine();
          continue;
        }

        if((billCode.equals("S01") || billCode.equals("W01") || billCode.equals("F01")) && amount.compareTo(Parameters.ZERO) > 0) {

          bill_date = postDate;
          bill_prd = transPrd;
          tran_type = "B";
          bill_ded_code = billCode;
          bill_year = year;
          flat_amt = amount;
          bill_type = "A";
          descript = "Billing Hist Conv";
          meter_group = "1";

          insertBilling(service, getInsertBillStatement(service));
        }
        else if(billCode.equals("S08") || billCode.equals("W08") || billCode.equals("F08")) { // Payment
          if(amount.compareTo(Parameters.ZERO) > 0)
            insertUtPaymentTableTrans(service, getInsertPaymentStatement(service), utm_id, billCode, "",
                transType.contains("INT") ? Parameters.ZERO : amount.abs(), transType.contains("INT") ? amount : Parameters.ZERO, "P", " ",
                " ", "N", postDate, year, transPrd, "Payment Hist Conv", "EA2", "SU", "N", Day.EMPTY_DAY);
          else
            // Credit Adjustment
            insertUtPaymentTableTrans(service, getInsertPaymentStatement(service), utm_id, billCode, "",
                transType.contains("INT") ? Parameters.ZERO : amount.abs(), transType.contains("INT") ? amount : Parameters.ZERO, "J", " ",
                " ", "N", postDate, year, transPrd, "Payment Hist Conv", "EA2", "SU", "N", Day.EMPTY_DAY);
        }
        else {
          insertUtPaymentTableTrans(service, getInsertPaymentStatement(service), utm_id, billCode, "",
              transType.contains("INT") ? Parameters.ZERO : amount, transType.contains("INT") ? amount : Parameters.ZERO, "J", " ", " ",
              "N", postDate, year, transPrd, "Adjustment Hist Conv", "EA2", "SU", "N", Day.EMPTY_DAY);
        }

        fileLine = reader.readLine();
      }
      System.out.println("Completed Transaction History");
      // Meter Info
      reader = new BufferedReader(new FileReader("Utility\\CSV Data\\MeterInfo.csv"));
      fileLine = reader.readLine();
      fileLine = reader.readLine();
      String prevAcct = "";
      int seq = 1;

      while(fileLine != null) {
        if(fileLine.trim().length() == 0) {
          fileLine = reader.readLine();
          continue;
        }
        HashMap fields = parseMetersFile(fileLine);

        if(fields.containsKey("AcctId")) {
          acctId = (String)fields.get("AcctId").toString().replace("\"", "").trim();
          utm_id = utmIdHashMap.get(acctId);
          if(utm_id == null) {
            System.out.println("Null Meter Id: " + acctId);
            fileLine = reader.readLine();
            continue;
          }

          if(acctId.equals(prevAcct))
            seq++;
          else
            seq = 1;
          prevAcct = acctId;
        }
        if(fields.containsKey("Book")) {
          meterBook = justify((String)fields.get("Book").toString().replace("\"", "").trim(), 5, 'R');
        }
        if(fields.containsKey("Page")) {
          meterPage = justify((String)fields.get("Page").toString().replace("\"", "").trim(), 5, 'R');
          if(meterPage.contains("-"))
            meterPage = justify(meterPage.substring(0, meterPage.length() - 2), 5, 'R');
        }
        if(fields.containsKey("Serial")) {
          meterSerialNum = (String)fields.get("Serial").toString().replace("\"", "").trim();
        }
        if(fields.containsKey("MeterNo")) {
          meterNum = (String)fields.get("MeterNo").toString().replace("\"", "").trim();
        }
        if(fields.containsKey("Size")) {
          meterDescript = (String)fields.get("Size").toString().replace("\"", "").trim();
          if(!meterDescript.isEmpty())
            meterDescript = "Size: " + meterDescript;
        }
        if(fields.containsKey("LocationCode")) {
          meterLocation = (String)fields.get("LocationCode").toString().replace("\"", "").trim();
          if(!meterLocation.isEmpty())
            meterLocation += ": ";
        }
        if(fields.containsKey("LocationMessage")) {
          meterLocation += (String)fields.get("LocationMessage").toString().replace("\"", "").trim();
        }
        if(fields.containsKey("ReadType")) {
          String readType = (String)fields.get("ReadType").toString().replace("\"", "").trim();

          if(readType.equals("R") || readType.equals("T"))
            meterGunRdgType = readType.charAt(0);
        }
        if(fields.containsKey("InstallDate")) {
          String tempDate = (String)fields.get("InstallDate").toString().replace("\"", "").replace("/", "").trim();
          if(tempDate.length() == 7)
            tempDate = "0" + tempDate;
          try {
            meterInstallDate = getDate(tempDate);
          }
          catch(IllegalArgumentException e) {
            meterInstallDate = Day.EMPTY_DAY;
          }
        }
        if(fields.containsKey("ServiceNote")) {// TODO
          String temp = (String)fields.get("ServiceNote").toString().replace("\"", "").replace("/", "").trim();
        }
        if(fields.containsKey("Note1")) {// TODO
          String temp = (String)fields.get("Note1").toString().replace("\"", "").replace("/", "").trim();
        }
        if(fields.containsKey("Note2")) {// TODO
          String temp = (String)fields.get("Note2").toString().replace("\"", "").replace("/", "").trim();
        }
        if(fields.containsKey("Note3")) {// TODO
          String temp = (String)fields.get("Note3").toString().replace("\"", "").replace("/", "").trim();
        }
        if(fields.containsKey("Active")) {
          String temp = (String)fields.get("Active").toString().replace("\"", "").replace("/", "").trim();
          meterStatus = temp.equals("Y") ? 'A' : 'R';
        }

        if(numDialsMap.containsKey(meterNum))
          meterNumDials = Integer.parseInt(numDialsMap.get(meterNum).trim());

        meterGunType = Parameters.NEPTUNE_FILE_TYPE;
        meterType = '1';
        meterSeq = seq;
        insertUtmeter(utm_id, meterServiceType, meterSeq, meterStatus, meterStatusReason, meterStatusDate, meterType, meterGroup, meterNum,
            meterSerialNum, meterRadioNum, meterLocation, meterBook, meterPage, meterNumDials, meterMult, meterMult2, meterGunType,
            meterGunRdgType, meterReadResol, meterBadgerMVRS, meterDescript, meterPipeSize, meterPipeFoot, meterInstallDate, meterMiscCode,
            meterReplacedSeq, meterLatitude, meterLongitude);
        initializeUtmeterVariables();

        fileLine = reader.readLine();
      }
      /** Manually insert meters from meter report not in detailedCustList */
      utm_id = utmIdHashMap.get("018002319");
      meterSerialNum = "90575296";
      meterNum = "1831809272";
      meterGunType = 'A';
      meterType = '1';
      meterSeq = 1;
      meterDescript = "Size: 5/8\"";
      meterLocation = "OVER WATER HEATER";
      meterBook = "  018";
      meterPage = "  030";
      meterInstallDate = getDate("06062011");
      insertUtmeter(utm_id, meterServiceType, meterSeq, meterStatus, meterStatusReason, meterStatusDate, meterType, meterGroup, meterNum,
          meterSerialNum, meterRadioNum, meterLocation, meterBook, meterPage, meterNumDials, meterMult, meterMult2, meterGunType,
          meterGunRdgType, meterReadResol, meterBadgerMVRS, meterDescript, meterPipeSize, meterPipeFoot, meterInstallDate, meterMiscCode,
          meterReplacedSeq, meterLatitude, meterLongitude);
      initializeUtmeterVariables();
      utm_id = utmIdHashMap.get("109112003");
      meterSerialNum = "77942725";
      meterNum = "1461166584";
      meterGunType = 'A';
      meterType = '1';
      meterSeq = 1;
      meterDescript = "Size: 5/8\"";
      meterLocation = "REAR UTILITY ROOM";
      meterBook = "  077";
      meterPage = "  198";
      meterInstallDate = getDate("11132006");
      insertUtmeter(utm_id, meterServiceType, meterSeq, meterStatus, meterStatusReason, meterStatusDate, meterType, meterGroup, meterNum,
          meterSerialNum, meterRadioNum, meterLocation, meterBook, meterPage, meterNumDials, meterMult, meterMult2, meterGunType,
          meterGunRdgType, meterReadResol, meterBadgerMVRS, meterDescript, meterPipeSize, meterPipeFoot, meterInstallDate, meterMiscCode,
          meterReplacedSeq, meterLatitude, meterLongitude);
      initializeUtmeterVariables();
      utm_id = utmIdHashMap.get("158000201");
      meterSerialNum = "PPPPPPPPHHKK";
      meterNum = "";
      meterGunType = 'A';
      meterType = '1';
      meterSeq = 1;
      meterDescript = "Size: 5/8\"";
      meterLocation = "";
      meterBook = "  099";
      meterPage = "  024";
      meterInstallDate = Day.EMPTY_DAY;
      insertUtmeter(utm_id, meterServiceType, meterSeq, meterStatus, meterStatusReason, meterStatusDate, meterType, meterGroup, meterNum,
          meterSerialNum, meterRadioNum, meterLocation, meterBook, meterPage, meterNumDials, meterMult, meterMult2, meterGunType,
          meterGunRdgType, meterReadResol, meterBadgerMVRS, meterDescript, meterPipeSize, meterPipeFoot, meterInstallDate, meterMiscCode,
          meterReplacedSeq, meterLatitude, meterLongitude);
      initializeUtmeterVariables();
      System.out.println("Completed Meters");

      // Meter Readings
      reader = new BufferedReader(new FileReader("Utility\\CSV Data\\ReadingHistory.csv"));
      fileLine = reader.readLine();
      fileLine = reader.readLine();
      BigDecimal prevRead = Parameters.ZERO;

      while(fileLine != null) {
        if(fileLine.trim().length() == 0) {
          fileLine = reader.readLine();
          continue;
        }
        Day rDate = Day.EMPTY_DAY;
        BigDecimal rdg = Parameters.ZERO;
        HashMap fields = parseMeterReadsFile(fileLine);

        if(fields.containsKey("AcctId")) {
          acctId = (String)fields.get("AcctId").toString().replace("\"", "").trim();

          if(utmIdHashMap.get(acctId) == null) {
            System.out.println("Null Read Id: " + acctId);
            fileLine = reader.readLine();
            continue;
          }
        }
        if(fields.containsKey("Date")) {
          String temp = (String)fields.get("Date").toString().replace("\"", "").trim();
          rDate = getDate(temp);
          if(rDate.getYear() < 2013) {
            fileLine = reader.readLine();
            continue;
          }
        }
        if(fields.containsKey("Reading")) {
          String temp = (String)fields.get("Reading").toString().replace("\"", "").trim();
          if(temp.equals("0")) {
            fileLine = reader.readLine();
            continue;
          }
          rdg = new BigDecimal(temp);
        }
        if(fields.containsKey("Usage")) {
          String temp = (String)fields.get("Usage").toString().replace("\"", "").trim();
        }
        if(fields.containsKey("Type")) {
          String temp = (String)fields.get("Type").toString().replace("\"", "").trim();
        }

        int month = rDate.getMonth();
        Boolean insRead = true;
        utm_id = utmIdHashMap.get(acctId);
        meter_group = "1";
        reading = rdg;
        rdg_type = "S";
        rdg_date = rDate;
        year = rDate.getYearString();
        meter_seq = getNextUtmeterSeq(utm_id, 'W') - 1;

        String tCycle = altIdCycleMap.get(acctId);
        if(tCycle.equals("A")) { // Cycle A
          if(month < 4)
            prd = 1;
          else if(month < 7)
            prd = 2;
          else if(month < 10)
            prd = 3;
          else if(month >= 10)
            prd = 4;
        }
        else if(tCycle.equals("B") || tCycle.equals("F")) { // Cycle B
          if(month > 1 && month < 5)
            prd = 1;
          else if(month > 4 && month < 8)
            prd = 2;
          else if(month > 7 && month < 11)
            prd = 3;
          else if(month > 10 || month == 1) {
            prd = 4;
            if(month == 1) {
              year = Integer.parseInt(year) - 1 + "";
            }
          }
        }
        else if(tCycle.equals("C")) { // Cycle C
          if(month > 2 && month < 6)
            prd = 1;
          else if(month > 5 && month < 9)
            prd = 2;
          else if(month > 8 && month < 12)
            prd = 3;
          else if(month > 11 || month < 3)
            prd = 4;
          if(month < 3) {
            year = Integer.parseInt(year) - 1 + "";
          }
        }
        else if(tCycle.equals("S") || tCycle.equals("L")) {
          prd = 1;
          if(month < 11) {
            year = Integer.parseInt(year) - 1 + "";
          }
        }
        else {
          System.out.println("Invalid Cycle: " + altIdCycleMap.get(acctId));
          insRead = false;
        }

        if(insRead) {
          if(prevAcct.equals(acctId) && rdg.compareTo(prevRead) == -1) {
            rdg_type = "R";
            reading = Parameters.ZERO;
            insertReading('W', getInsertReadingStatement('W'));
            rdg_type = "S";
            reading = rdg;
            insertReading('W', getInsertReadingStatement('W'));
          }
          else
            insertReading('W', getInsertReadingStatement('W'));

          prevAcct = acctId;
          prevRead = rdg;
        }

        fileLine = reader.readLine();
      }
      System.out.println("Completed Readings");

      // Resequence Book and Page
      int count = 0;
      reader = new BufferedReader(new FileReader("Utility\\CSV Data\\BPReSeq.csv"));
      fileLine = reader.readLine();
      fileLine = reader.readLine();
      String prevBook = "";

      while(fileLine != null) {
        if(fileLine.trim().length() == 0) {
          fileLine = reader.readLine();
          continue;
        }
        String page = "";
        String book = "";
        HashMap fields = parseBPReSeqFile(fileLine);

        if(fields.containsKey("UtmId")) {
          String temp = (String)fields.get("UtmId").toString().replace("\"", "").trim();
          int utm = Integer.parseInt(temp.substring(0, temp.length() - 3));
          int sub = Integer.parseInt(temp.charAt(temp.length() - 1) + "");
          utm_id = formatUtmId(utm, sub);
        }
        if(fields.containsKey("Status")) {
          String temp = (String)fields.get("Status").toString().replace("\"", "").trim();
        }
        if(fields.containsKey("Book")) {
          book = (String)fields.get("Book").toString().replace("\"", "").trim();
        }
        if(fields.containsKey("NewPage")) {
          page = (String)fields.get("NewPage").toString().replace("\"", "").trim();
        }
        page = justify(page + "", 5, 'R');
        statement.execute("UPDATE Utmeters SET Page = '" + page + "' WHERE Utm_Id = '" + utm_id + "'");

        fileLine = reader.readLine();
      }

      // Bill Codes
      statement.execute(getInsertBillCodeSql("S01", "S", "Sewer", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W01", "W", "Water", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("F01", "O", "Fire", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("H01", "O", "Hydrant", "", "D", "N", "N", "", ""));
      // Obtained from Rate Structure List
      statement.execute(getInsertBillCodeSql("W10", "W", "Resident 5/8\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W11", "W", "Resident 3/4\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W12", "W", "Resident 1\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W13", "W", "Resident 1 1/2\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W14", "W", "Resident 2\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W15", "W", "Resident 3\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W16", "W", "Resident 4\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W20", "W", "Apartment", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W30", "W", "Community 5/8\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W31", "W", "Community 3/4\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W32", "W", "Community 1\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W33", "W", "Community 1 1/2\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W34", "W", "Community 2\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W35", "W", "Community 3\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W36", "W", "Community 6\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W70", "W", "Industrial 5/8\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W71", "W", "Industrial 3/4\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W72", "W", "Industrial 1\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W73", "W", "Industrial 1 1/2\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W74", "W", "Industrial 2\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W75", "W", "Industrial 3\"", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("W76", "W", "Industrial 4\"", "", "D", "N", "N", "", ""));

      statement.execute(getInsertBillCodeSql("S10", "S", "Resident", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("S11", "S", "Resident/TRAIL", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("S12", "S", "Condominium/Cluster", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("S20", "S", "Multi-Family", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("S30", "S", "Community", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("S40", "S", "Condominium", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("S50", "S", "School", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("S52", "S", "Senior Discount", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("S53", "S", "Trailer", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("S54", "S", "Trailer SR", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("S55", "S", "Cluster", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("S56", "S", "Cluster SR", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("S60", "S", "Fire Serv", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("S61", "S", "Swim Sewer", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("S70", "S", "Industrial", "", "D", "N", "N", "", ""));

      statement.execute(getInsertBillCodeSql("F87", "O", "10\" Serv", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("F88", "O", "Flat", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("F89", "O", "(1)2\" (1)4\" Line", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("F90", "O", "(1)8\" (2)6\" Line", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("F91", "O", "Fire Hydrant", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("F92", "O", "2\" Fire Line", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("F93", "O", "2.5\" Fire Line", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("F94", "O", "3\" Fire Line", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("F95", "O", "4\" Fire Line", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("F96", "O", "6\" Fire Line", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("F97", "O", "8\" Fire Line", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("F98", "O", "Under 2\" Line", "", "D", "N", "N", "", ""));
      statement.execute(getInsertBillCodeSql("F99", "O", "(8)2\" (1)4\" Line", "", "D", "N", "N", "", ""));

      // Adjustment Codes
      statement.execute(getInsertAdjCodeSql("ZMS", "Misc", "4"));
      statement.execute(getInsertAdjCodeSql("W08", "Credit Adjustment", "4"));
      statement.execute(getInsertAdjCodeSql("S08", "Credit Adjustment", "4"));
      statement.execute(getInsertAdjCodeSql("F08", "Credit Adjustment", "4"));

      statement.execute(getInsertAdjCodeSql("S22", "Comm Bill Adj", "4"));
      statement.execute(getInsertAdjCodeSql("W20", "Comm Bill Adj", "4"));
      statement.execute(getInsertAdjCodeSql("S06", "Corrected Int", "4"));
      statement.execute(getInsertAdjCodeSql("W06", "Corrected Int", "4"));
      statement.execute(getInsertAdjCodeSql("S03", "Corrections", "4"));
      statement.execute(getInsertAdjCodeSql("W03", "Corrections", "4"));
      statement.execute(getInsertAdjCodeSql("W22", "Final Rd Fee", "4"));
      statement.execute(getInsertAdjCodeSql("S25", "Int Chg Adj", "4"));
      statement.execute(getInsertAdjCodeSql("W15", "Int Chg Adj", "4"));
      statement.execute(getInsertAdjCodeSql("F15", "Int Chg Adj", "4"));
      statement.execute(getInsertAdjCodeSql("F11", "Billing Adj", "4"));
      statement.execute(getInsertAdjCodeSql("S26", "Int Pmt Adj", "4"));
      statement.execute(getInsertAdjCodeSql("W16", "Int Pmt Adj", "4"));
      statement.execute(getInsertAdjCodeSql("S05", "Interest", "4"));
      statement.execute(getInsertAdjCodeSql("W05", "Interest", "4"));
      statement.execute(getInsertAdjCodeSql("F05", "Interest", "4"));
      statement.execute(getInsertAdjCodeSql("S27", "Princ Pmt Adj", "4"));
      statement.execute(getInsertAdjCodeSql("W17", "Princ Pmt Adj", "4"));
      statement.execute(getInsertAdjCodeSql("S21", "Res Bill Adj", "4"));
      statement.execute(getInsertAdjCodeSql("W11", "Res Bill Adj", "4"));
      statement.execute(getInsertAdjCodeSql("S12", "Ret Check Fee", "4"));
      statement.execute(getInsertAdjCodeSql("W12", "Ret Check Fee", "4"));
      statement.execute(getInsertAdjCodeSql("S23", "Ret CK - Intrst", "4"));
      statement.execute(getInsertAdjCodeSql("W19", "Ret CK - Intrst", "4"));
      statement.execute(getInsertAdjCodeSql("S24", "Ret CK - Princp", "4"));
      statement.execute(getInsertAdjCodeSql("W14", "Ret CK - Princp", "4"));
      statement.execute(getInsertAdjCodeSql("W13", "Water Off Chg", "4"));
      statement.execute(getInsertAdjCodeSql("S28", "Overpayment", "4"));
      statement.execute(getInsertAdjCodeSql("ZOB", "Open Balance", "4"));
      statement.execute(getInsertAdjCodeSql("ZRN", "Remaining Interest", "4"));
      statement.execute(getInsertAdjCodeSql("ZTR", "Transfer Credits", "4"));
      statement.execute(getInsertAdjCodeSql("ZZZ", "Accrued Revenue", "4"));

      // Pay Codes
      statement.execute(getInsertPayCodeSql("S08", "S", "", "Sewer Payment", "ZCONVERT", "", "N"));
      statement.execute(getInsertPayCodeSql("W08", "W", "", "Water Payment", "ZCONVERT", "", "N"));
      statement.execute(getInsertPayCodeSql("F08", "O", "", "Fire Payment", "ZCONVERT", "", "N"));
      statement.execute(getInsertPayCodeSql("ZSP", "S", "", "Overpayment", "ZCONVERT", "", "N"));
      statement.execute(getInsertPayCodeSql("ZWP", "W", "", "Overpayment", "ZCONVERT", "", "N"));
      statement.execute(getInsertPayCodeSql("ZOP", "O", "", "Overpayment", "ZCONVERT", "", "N"));

      // Cycle A
      active_sw1 = "Y";
      active_sw2 = "Y";
      active_sw3 = "Y";
      active_sw4 = "Y";
      from_date1 = new Day(1996, 1, 1);
      from_date2 = new Day(1996, 4, 1);
      from_date3 = new Day(1996, 7, 1);
      from_date4 = new Day(1996, 10, 1);
      to_date1 = new Day(1996, 3, 31);
      to_date2 = new Day(1996, 6, 30);
      to_date3 = new Day(1996, 9, 30);
      to_date4 = new Day(1996, 12, 31);
      due_date1 = new Day(1996, 4, 30);
      due_date2 = new Day(1996, 7, 31);
      due_date3 = new Day(1996, 10, 31);
      due_date4 = new Day(1997, 1, 31);
      year = "1996";
      cycle_num = "  1";
      insertCycle('S');
      insertCycle('W');

      // Cycles B,F
      active_sw1 = "Y";
      active_sw2 = "Y";
      active_sw3 = "Y";
      active_sw4 = "Y";
      from_date1 = new Day(1996, 2, 1);
      from_date2 = new Day(1996, 5, 1);
      from_date3 = new Day(1996, 8, 1);
      from_date4 = new Day(1996, 11, 1);
      to_date1 = new Day(1996, 4, 30);
      to_date2 = new Day(1996, 7, 31);
      to_date3 = new Day(1996, 10, 31);
      to_date4 = new Day(1997, 1, 31);
      due_date1 = new Day(1996, 5, 31);
      due_date2 = new Day(1996, 8, 31);
      due_date3 = new Day(1996, 11, 30);
      due_date4 = new Day(1997, 2, 28);
      year = "1996";
      cycle_num = "  2";
      insertCycle('S');
      insertCycle('W');
      insertCycle('O');

      // Cycle C
      active_sw1 = "Y";
      active_sw2 = "Y";
      active_sw3 = "Y";
      active_sw4 = "Y";
      from_date1 = new Day(1996, 3, 1);
      from_date2 = new Day(1996, 6, 1);
      from_date3 = new Day(1996, 9, 1);
      from_date4 = new Day(1996, 12, 1);
      to_date1 = new Day(1996, 5, 31);
      to_date2 = new Day(1996, 7, 31);
      to_date3 = new Day(1996, 11, 30);
      to_date4 = new Day(1997, 2, 28);
      due_date1 = new Day(1996, 6, 30);
      due_date2 = new Day(1996, 9, 30);
      due_date3 = new Day(1996, 12, 31);
      due_date4 = new Day(1997, 3, 31);
      year = "1996";
      cycle_num = "  3";
      insertCycle('S');
      insertCycle('W');

      // Cycles S,L
      active_sw1 = "Y";
      from_date3 = new Day(1996, 11, 1);
      to_date3 = new Day(1997, 10, 31);
      due_date3 = new Day(1997, 11, 30);
      year = "1996";
      cycle_num = "  4";
      insertCycle('S');
      insertCycle('W');
      rollCycles("1996", "1997");
      rollCycles("1997", "1998");
      rollCycles("1998", "1999");
      rollCycles("1999", "2000");
      rollCycles("2000", "2001");
      rollCycles("2001", "2002");
      rollCycles("2002", "2003");
      rollCycles("2003", "2004");
      rollCycles("2004", "2005");
      rollCycles("2005", "2006");
      rollCycles("2006", "2007");
      rollCycles("2007", "2008");
      rollCycles("2008", "2009");
      rollCycles("2009", "2010");
      rollCycles("2010", "2011");
      rollCycles("2011", "2012");
      rollCycles("2012", "2013");
      rollCycles("2013", "2014");
      rollCycles("2014", "2015");
      rollCycles("2015", "2016");
    }
    finally {
      System.err.println(fileLine);
      reader.close();

    }
  }

  public Day getDate(String date) {
    Day newDay = new Day();

    if(!date.contains("/")) {
      if(date.isEmpty() || date.equals("0") || date.equals("00000000"))
        return Day.EMPTY_DAY;
      if(date.length() == 7)
        date = "0" + date;
      int month = Integer.parseInt(date.substring(0, 2));
      int day = Integer.parseInt(date.substring(2, 4));
      String tempYear = date.substring(4);

      if(tempYear.length() == 2) {
        if(tempYear.charAt(0) != '0' && tempYear.charAt(0) != '1')
          tempYear = "19" + tempYear;
        else
          tempYear = "20" + tempYear;
      }
      int year = Integer.parseInt(tempYear);

      try {
        if(year <= 2016) {
          if(year < 2016) {
            newDay = new Day(year, month, day);
          }
          else if(year == 2016 && month < 3) {
            newDay = new Day(year, month, day);
          }
          else {
            newDay = Day.EMPTY_DAY;
          }
        }
        else {
          newDay = Day.EMPTY_DAY;
        }
      }
      catch(Exception ex) {
        newDay = Day.EMPTY_DAY;
      }
      return newDay;
    }

    String[] dmy = date.split("/");
    try {
      return new Day(Integer.parseInt(dmy[2]), Integer.parseInt(dmy[0]), Integer.parseInt(dmy[1]));
    }
    catch(Exception e) {
      System.out.println("Invalid date: " + date);
      return Day.EMPTY_DAY;
    }
  }

  // Adds spaces to field values to meet length requirement
  private String justify(String value, int size, char pos) {
    int length = value.length();

    if(pos == 'R') {
      for(int i = 0; i < size - length; i++) {
        value = " " + value;
      }
    }
    else {
      for(int i = 0; i < size - length; i++) {
        value = value + " ";
      }
    }
    return value;
  }

  public void initializeUtmeterVariables() {
    meterServiceType = 'W';
    meterSeq = 1;
    meterStatus = 'A';
    meterStatusReason = "";
    meterStatusDate = Day.EMPTY_DAY;
    meterType = ' ';
    meterGroup = '1';
    meterNum = "";
    meterSerialNum = "";
    meterRadioNum = "";
    meterLocation = "";
    meterBook = "";
    meterPage = "";
    meterNumDials = 4;
    meterMult = 0;
    meterMult2 = 0;
    meterGunType = ' ';
    meterGunRdgType = 'R';
    meterReadResol = "";
    meterBadgerMVRS = "";
    meterDescript = "";
    meterPipeSize = Parameters.ZERO;
    meterPipeFoot = "";
    meterInstallDate = Day.EMPTY_DAY;
    meterMiscCode = "";
    meterReplacedSeq = 0;
    meterLatitude = Parameters.ZERO;
    meterLongitude = Parameters.ZERO;
  }

  // Used for city_block_lot_qual field
  private String getBlqId(String block, String subB, String lot, String subL, String qual) {
    String blqId = "";

    blqId += justify(block, 5, 'R');
    blqId += justify(subB, 4, 'R');
    blqId += justify(lot, 5, 'R');
    blqId += justify(subL, 4, 'R');

    switch(qual.length()) {
      case 0:
        blqId += "           ";
        break;
      case 1: // Q3
        blqId += "        " + qual + "  ";
        break;
      case 2: // Q4
        blqId += "         " + qual;
        break;
      case 3: // Q1
        blqId += qual + "        ";
        break;
      case 5: // Q2
        blqId += "   " + qual + "   ";
      default:
        blqId += justify(qual, 11, 'L');
        break;
    }

    return blqId;
  }

  private String[] getCityStZip(String value) {
    String citySt = value.substring(0, value.length() - 5);
    String zip = value.substring(value.length() - 5);

    if(zip.matches(".*[A-Za-z].*")) {
      zip = "";
    }

    else if(zip.startsWith("-")) {
      citySt = value.substring(0, value.length() - 10);
      zip = value.substring(value.length() - 10);
    }

    String[] cityStZip = {citySt, zip};
    return cityStZip;
  }
}
