package ManageSystem;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GradeManage extends JPanel
{
    ArrayList<Student> stu = new ArrayList<Student>();

    public static void main(String[] args)
    {
        JFrame frame=new JFrame("GradeManage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GradeManage(),BorderLayout.BEFORE_FIRST_LINE);
        frame.setVisible(true);
        frame.setBounds(700,200,400,400);
        frame.setResizable(false);

    }

    public GradeManage() {
        super(new GridLayout(1, 1));
        JTabbedPane tabbedPane = new JTabbedPane();
        JComponent panel1 = pageInput();
        tabbedPane.addTab("input",panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        JComponent panel2 = pageSearch();
        tabbedPane.addTab("search",panel2);
        JComponent panel3 = pageSort();
        tabbedPane.addTab("sort",panel3);
        add(tabbedPane);
    }

    protected JComponent pageInput()
    {
        JPanel panel=new JPanel(false);
        JButton filler=new JButton("input");
        JLabel num = new JLabel("Id");
        JLabel grade = new JLabel("Grade");
        panel.setLayout(new GridLayout(13,1));
        TextField StudentNum = new TextField();
        TextField StudentGrade = new TextField();
        panel.add(num);
        num.setHorizontalAlignment(JLabel.CENTER);
        panel.add(StudentNum);
        panel.add(grade);
        grade.setHorizontalAlignment(JLabel.CENTER);
        panel.add(StudentGrade);
        panel.add(filler);
        InputAction1 myActionListener = new InputAction1(StudentNum,StudentGrade,stu);
        filler.addActionListener(myActionListener);
        return panel;
    }

    protected JComponent pageSearch()
    {
        JPanel panel=new JPanel(false);
        JButton filler=new JButton("search");
        JLabel num = new JLabel("Id");
        JLabel grade = new JLabel("Grade");
        panel.setLayout(new GridLayout(13,1));
        TextField StudentNum = new TextField();
        TextField StudentGrade = new TextField();
        panel.add(num);
        num.setHorizontalAlignment(JLabel.CENTER);
        panel.add(StudentNum);
        panel.add(filler);
        panel.add(grade);
        num.setHorizontalAlignment(JLabel.CENTER);
        panel.add(StudentGrade);
        InputAction2 myActionListener = new InputAction2(StudentNum,StudentGrade,stu);
        filler.addActionListener(myActionListener);
        return panel;
    }

    protected JComponent pageSort()
    {
        JPanel panel=new JPanel(false);
        JButton filler=new JButton("sort");
//        TextField StudentNum = new TextField();
//        TextField StudentGrade = new TextField();
        panel.add(filler);
        InputAction3 myActionListener = new InputAction3(stu);
        filler.addActionListener(myActionListener);
        return panel;
    }
}

//响应input按钮事件
class InputAction1 implements ActionListener {

    public TextField StudentNum,StudentGrade;
    private   ArrayList<Student> st;
    public InputAction1(TextField StudentNum,TextField StudentGrade,ArrayList<Student> st){
        this.StudentNum = StudentNum;
        this.StudentGrade = StudentGrade;
        this.st=st;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取文本框里面的信息
        //将信息保存
        Student s = new Student(StudentNum.getText(),Integer.parseInt(StudentGrade.getText()));
        st.add(s);
        StudentGrade.setText("");
        StudentNum.setText("");
    }
}

//响应Search按钮事件
class InputAction2 implements ActionListener {

    public String line;
    private int len;
    public TextField StudentNum,StudentGrade;
    private   ArrayList<Student> st;
    public InputAction2(TextField StudentNum,TextField StudentGrade,ArrayList<Student> st){
        this.StudentNum = StudentNum;
        this.StudentGrade = StudentGrade;
        this.st = st;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        line = StudentNum.getText();
        len = st.size();
        for(int i=0;i<len;i++){
            if(line.equals(st.get(i).id)){
                StudentGrade.setText(String.valueOf(st.get(i).grade));
                break;
            }
        }
    }
}

//响应Sort按钮事件
class InputAction3 implements ActionListener {
    public int len;
    private   ArrayList<Student> st;
    JPanel sort;
    public InputAction3(ArrayList<Student> st){
        this.st = st;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Student s = null;
        len = st.size();
        String[] columnNames = {"Rank","Id", "Grade"};
        for(int k=0;k<len;k++)
        {
            for(int i=k+1;i<len-1;i++)
            {
                if(st.get(k).grade<st.get(i).grade)
                {
                    s = st.get(k);
                    st.get(k).grade = st.get(i).grade;
                    st.get(k).id = st.get(i).id;
                    st.get(i).grade = s.grade;
                    st.get(i).id = s.id;
                }
            }
        }
        for(int i=0;i<len;i++)
        {
            System.out.println(st.get(i).id +"  " + st.get(i).grade);
        }
//        Object[][] tableDate=new Object[len][3];
//        for(int i=0;i<len;i++){
//            tableDate[i][0]=i+1;
//            tableDate[i][1]=st.get(i).id;
//            tableDate[i][2]=st.get(i).grade;
//        }
//        JTable table = new JTable(tableDate, columnNames);
//        sort.add(new JScrollPane(table));
    }
}

//存储学生成绩的类
class Student{
    String id;
    int grade;
    public Student(String n,int s){
        id = n;
        grade = s;
    }
}
