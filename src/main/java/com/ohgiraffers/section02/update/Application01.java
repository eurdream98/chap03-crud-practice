package com.ohgiraffers.section02.update;

import com.ohgiraffers.model.dto.DepartmentDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {
    public static void main(String[] args) {
        Connection con = getConnection();
        int result  = 0;
        PreparedStatement pstmt = null;
        DepartmentDTO departmentDTO = null;
        Properties prop = new Properties();
        Scanner sc = new Scanner(System.in);
        System.out.print("변경할 부서의 아이디를 입력해주세요 : ");
        String deptId = sc.nextLine();
        System.out.print("부서명을 변경해주세요 : ");
        String deptTitle = sc.nextLine();
        System.out.print("국가명을 수정해주세요 : ");
        String locationId = sc.nextLine();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/connection-info.xml"));
            String query = prop.getProperty("update");
            pstmt = con.prepareStatement(query);

            pstmt.setString(1,deptTitle);
            pstmt.setString(2,locationId);
            pstmt.setString(3,deptId);
            result = pstmt.executeUpdate();


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(pstmt);
            close(con);
        }
        if(result>0){
            System.out.println("수정이 정상적으로 완료되었습니다. *^^*");
        }else{
            System.out.println("수정에 실패하였습니다. ㅠㅠ");
        }
    }
}
