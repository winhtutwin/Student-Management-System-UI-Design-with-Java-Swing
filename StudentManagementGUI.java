import javax.swing.*; // ခလုတ်၊ စာရိုက်ကွက်၊ ဇယား စသည့် GUI Component များအတွက်
import javax.swing.border.EmptyBorder; // ဘေးဘောင် (Margin/Padding) ခြားရန်အတွက်
import javax.swing.table.DefaultTableModel; // ဇယား (Table) အတွင်းရှိ Data များကို စီမံရန်အတွက်
import java.awt.*; // အရောင်၊ ဖောင့် နှင့် Layout ပုံစံချထားမှုများအတွက်

// Window တစ်ခုဖန်တီးရန် JFrame ကို extends လုပ်ထားပါသည်
public class StudentManagementGUI extends JFrame {

    public StudentManagementGUI() {
        // --- ၁။ Main Frame (အဓိက Window ပြင်ဆင်ခြင်း) ---
        setTitle("Student Management System (UI Design)"); // Window ရဲ့ ခေါင်းစဉ်နာမည်
        setSize(1000, 600); // Window ရဲ့ အရွယ်အစား (အကျယ် x အမြင့်)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X ခလုတ်နှိပ်ပါက ပရိုဂရမ်ပိတ်သွားစေရန်
        setLocationRelativeTo(null); // Window ကို Screen ရဲ့ အလယ်တည့်တည့်မှာ ပေါ်စေရန်
        setLayout(new BorderLayout(10, 10)); // အပေါ်၊ အောက်၊ ဘယ်၊ ညာ နေရာခွဲခြားရန် BorderLayout ကို သုံးထားသည်

        // --- ၂။ Title Panel (အပေါ်ပိုင်း ခေါင်းစဉ်) ---
        // အတန်း (၂) တန်း၊ ကော်လံ (၁) ခုပါသော GridLayout ဖြင့် Panel ဖန်တီးခြင်း
        JPanel titlePanel = new JPanel(new GridLayout(2, 1)); 
        
        JLabel mainTitle = new JLabel("Student Management System (UI Design)", SwingConstants.CENTER);
        mainTitle.setFont(new Font("Serif", Font.BOLD, 28)); // စာလုံးဖောင့်နှင့် အရွယ်အစားသတ်မှတ်ခြင်း
        
        JLabel subTitle = new JLabel("Java Swing", SwingConstants.CENTER);
        subTitle.setFont(new Font("Serif", Font.BOLD, 18));
        subTitle.setForeground(new Color(0, 100, 0)); // စာသားအရောင်ကို အစိမ်းရင့်ရောင် ပေးခြင်း
        
        titlePanel.add(mainTitle); // Panel ထဲသို့ ခေါင်းစဉ်ကြီးထည့်ခြင်း
        titlePanel.add(subTitle); // Panel ထဲသို့ ခေါင်းစဉ်ငယ်ထည့်ခြင်း
        titlePanel.setBorder(new EmptyBorder(10, 0, 10, 0)); // အပေါ်အောက် နေရာအနည်းငယ်ချန်ခြင်း
        
        add(titlePanel, BorderLayout.NORTH); // ဒီ Title Panel ကို Window ရဲ့ အပေါ်ဆုံး (မြောက်ဘက်) တွင်ထားခြင်း

        // --- ၃။ Center Panel (အလယ်ပိုင်း - စာရိုက်ကွက်များနှင့် ဇယား) ---
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(new EmptyBorder(0, 10, 0, 10));

        // ၃.၁ Input Fields (အချက်အလက် ဖြည့်သွင်းရန် အကွက်များ)
        // အတန်း (၂) တန်း၊ ကော်လံ (၄) ခုပါသော အကွက်များ ဖန်တီးခြင်း
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 15, 10));
        
        // ပထမ အတန်း
        inputPanel.add(createInputComponent("Name*", new JTextField())); // စာရိုက်ထည့်ရန် JTextField
        inputPanel.add(createInputComponent("Mobile Number*", new JTextField()));
        inputPanel.add(createInputComponent("Age*", new JTextField()));
        
        // Gender အတွက် Radio Buttons များ
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JRadioButton rMale = new JRadioButton("Male");
        JRadioButton rFemale = new JRadioButton("Female");
        JRadioButton rOther = new JRadioButton("Other");
        
        // ButtonGroup ထဲထည့်မှသာ တစ်ခုရွေးလျှင် ကျန်တစ်ခု Auto ပျောက်မည်
        ButtonGroup bg = new ButtonGroup();
        bg.add(rMale); bg.add(rFemale); bg.add(rOther);
        genderPanel.add(rMale); genderPanel.add(rFemale); genderPanel.add(rOther);
        inputPanel.add(createInputComponent("Gender*", genderPanel));

        // ဒုတိယ အတန်း
        inputPanel.add(createInputComponent("Email*", new JTextField()));
        
        // Dropdown ရွေးချယ်စရာအတွက် JComboBox ကို အသုံးပြုခြင်း
        String[] dates = {"Sunday, January 15, 2023", "Monday, January 16, 2023"}; 
        inputPanel.add(createInputComponent("Admission Date*", new JComboBox<>(dates)));
        
        String[] classes = {"7th class", "8th class", "9th class", "10th class"};
        inputPanel.add(createInputComponent("Class*", new JComboBox<>(classes)));
        
        inputPanel.add(createInputComponent("Search By Name", new JTextField()));

        centerPanel.add(inputPanel, BorderLayout.NORTH); // Input များကို Center Panel ရဲ့ အပေါ်ဘက်တွင်ထားခြင်း

        // ၃.၂ Table Area (ဇယားဖန်တီးခြင်း)
        // ဇယား၏ ခေါင်းစဉ် (Column) များ
        String[] columns = {"ID", "Name", "Age", "Mobile Number", "Email", "Gender", "Class", "Date"};
        
        // ဇယားအတွင်း ပြသမည့် အချက်အလက်များ (2D Array)
        Object[][] data = {
                {"1", "Jane Smith", "18", "123-456-7890", "jane.smith@gmail.com", "Female", "7th class", "9/1/2022"},
                {"2", "John Doe", "19", "987-654-3210", "john.doe@gmail.com", "Male", "8th class", "9/1/2022"},
                {"3", "Emily Johnson", "20", "555-123-4567", "emily.johnson@gmail.com", "Female", "9th class", "9/1/2022"},
                {"4", "Michael Williams", "21", "111-222-3333", "michael.williams@gmail.com", "Male", "10th class", "9/1/2022"},
                {"5", "Samantha Brown", "17", "444-555-6666", "samantha.brown@gmail.com", "Female", "9th class", "9/1/2022"}
        };

        // Data များကို DefaultTableModel သို့ထည့်ပြီး JTable တည်ဆောက်ခြင်း
        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        table.setRowHeight(25); // ဇယား၏ အတန်းအမြင့်ကို ချိန်ညှိခြင်း
        
        // အချက်အလက်များလျှင် Scroll ဆွဲကြည့်နိုင်ရန် JScrollPane ထဲသို့ Table ကိုထည့်ခြင်း
        JScrollPane scrollPane = new JScrollPane(table); 
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER); // Center Panel တစ်ခုလုံးကို Window ရဲ့ အလယ်တွင်ထားခြင်း

        // --- ၄။ Bottom Buttons Panel (အောက်ခြေမှ ခလုတ်များ) ---
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.setBackground(Color.LIGHT_GRAY);

        // ဘယ်ဘက်ခြမ်း ခလုတ်များ
        JPanel leftButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftButtons.setBackground(Color.LIGHT_GRAY);
        leftButtons.add(new JButton("Exit"));
        leftButtons.add(new JButton("Refresh"));
        leftButtons.add(new JButton("Help"));

        // ညာဘက်ခြမ်း ခလုတ်များ
        JPanel rightButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButtons.setBackground(Color.LIGHT_GRAY);
        rightButtons.add(new JButton("Save"));
        rightButtons.add(new JButton("Update"));
        rightButtons.add(new JButton("Delete"));

        bottomPanel.add(leftButtons, BorderLayout.WEST); // ဘယ်ဘက်သို့ကပ်ရန်
        bottomPanel.add(rightButtons, BorderLayout.EAST); // ညာဘက်သို့ကပ်ရန်

        add(bottomPanel, BorderLayout.SOUTH); // ခလုတ်များကို Window အောက်ဆုံး (တောင်ဘက်) တွင်ထားခြင်း
    }

    // --- ၅။ Helper Method (အကွက်များလွယ်ကူစွာ ဖန်တီးရန်) ---
    // Label ကို အပေါ်မှာထားပြီး Input ကို အောက်မှာထားကာ Panel တစ်ခုအနေဖြင့် ပေါင်းပေးသော Method
    private JPanel createInputComponent(String labelText, JComponent inputComponent) {
        JPanel panel = new JPanel(new BorderLayout(0, 5));
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(label, BorderLayout.NORTH); // စာသားကို အပေါ်မှာထားသည်
        panel.add(inputComponent, BorderLayout.CENTER); // Input အကွက်ကို အလယ်မှာထားသည်
        return panel;
    }

    // --- ၆။ Main Method (ပရိုဂရမ် စတင်ရာနေရာ) ---
    public static void main(String[] args) {
        // ကိုယ်သုံးနေသည့် OS (ဥပမာ- Windows) ၏ Design အတိုင်း GUI ကို လိုက်ပြောင်းပေးရန်
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // GUI Window ကို စတင်ဖွင့်လှစ်ခြင်း
        SwingUtilities.invokeLater(() -> {
            new StudentManagementGUI().setVisible(true);
        });
    }
}
