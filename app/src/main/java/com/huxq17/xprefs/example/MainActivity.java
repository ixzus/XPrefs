package com.huxq17.xprefs.example;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.huxq17.xprefs.LogUtils;
import com.huxq17.xprefs.XPrefs;
import com.huxq17.xprefs.example.interfaces.IEmployee;
import com.huxq17.xprefs.example.interfaces.IStudent;
import com.huxq17.xprefs.example.interfaces.IUser;

public class MainActivity extends AppCompatActivity {
    private String spFile1 = "spfile1";
    private String spFile2 = "spfile2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveAll();
        save();

        saveAllAndFollowYourHeart();
        saveAllAndFollowYourHeartToo();
        saveAllAndFollowYourHeartThree();
    }

    /**
     * 单个字段的存储和读取
     */
    public void save() {
        //修改存储的sharedpreferences文件，mode默认为Context.MODE_PRIVATE
        XPrefs.changeFileName(spFile2);
        UserBean userBean = new UserBean();
        userBean.setAge(42);
        userBean.setFuns(2000);
        userBean.setMoney(200);
        userBean.setName("李雷");
        userBean.setVIP(false);
        XPrefs.save(userBean, "name");
        XPrefs.save(userBean, "age");
        XPrefs.save(userBean, "funs");
        XPrefs.save(userBean, "money");
        XPrefs.save(userBean, "isVIP");
        //读取，查看存入的数据
        Class cls = UserBean.class;
        boolean isVIP = XPrefs.getBoolean(cls, "isVIP");
        String name = XPrefs.getString(cls, "name");
        int age = XPrefs.getInt(cls, "age");
        long funs = XPrefs.getLong(cls, "funs");
        float money = XPrefs.getFloat(cls, "money");
        LogUtils.i("save name=" + name + ";money=" + money + ";age=" + age + ";funs=" + funs + ";isVIP=" + isVIP);
        //整取
        userBean = XPrefs.get(UserBean.class);
        LogUtils.i("save " + userBean.toString());
    }

    /**
     * 整存整取，把javabean类中的所有有效的字段都写入sharedpreferences中
     */
    private void saveAll() {
        //如果想切换写入的sharedpreferences文件，可以调用
//        XPrefs.changeFileName("your custom sp file's name");
        //如果设置Mode，如果调用了changeFileName方法，则必须在changeFileName之后调用
        XPrefs.changeFileMode(Context.MODE_PRIVATE);
        //或者直接调用
//        XPrefs.changeFileNameAndMode("your custom sp file's name", Context.MODE_PRIVATE);

        XPrefs.changeFileName(spFile1);
        UserBean userBean = new UserBean();
        userBean.setAge(21);
        userBean.setFuns(1000);
        userBean.setMoney(100);
        userBean.setName("韩梅梅");
        userBean.setVIP(true);
        XPrefs.saveAll(userBean);
        //读取，查看存入的数据
        userBean = XPrefs.get(UserBean.class);
        LogUtils.i("saveAll " + userBean);
    }

    private void saveAllAndFollowYourHeart() {
        IUser user = XPrefs.getObject(IUser.class);
        user.setName("Tom");
        user.setAge(18);
        user.setFuns(4000);
        user.setMoney(40000);
        user.setVip(true);
        LogUtils.i("IUser name=" + user.getName() + ";money=" + user.getMoney() + ";age=" + user.getAge()
                + ";funs=" + user.getFuns() + ";isVIP=" + user.getVip());
    }

    private void saveAllAndFollowYourHeartToo() {
        IEmployee employee = XPrefs.getObject(IEmployee.class);
        employee.setName("员工");
        employee.setAge(22);
        employee.setSalary(3000);
        LogUtils.i("IEmployee name=" + employee.getName() + ";age=" + employee.getAge() + ";salary=" + employee.getSalary());
    }

    private void saveAllAndFollowYourHeartThree() {
        IStudent student = XPrefs.getObject(IStudent.class);
        student.name("学生3号");
        student.score(100);
        student.sex("女");
        LogUtils.i("IStudent name=" + student.name() + ";score=" + student.score() + ";sex=" + student.sex());
    }

}
