package com.vku.main;

import com.vku.main.SupportXML;
import java.util.List;
import org.w3c.dom.Element;

public class CRUD_Lib {
    public static void main(String[] args) {
        SupportXML supportXML = new SupportXML();
        List<Element> elements = supportXML.read("E:\\Netbeans\\Workspace\\ThiGK\\src\\filexml.xml", "sinhvien");
        
        // Kiểm tra xem danh sách các phần tử có dữ liệu hay không
        if (!elements.isEmpty()) {
            Element firstElement = elements.get(0);
            String id = firstElement.getElementsByTagName("id").item(0).getTextContent();
            System.out.println("ID của phần tử đầu tiên: " + id);
        } else {
            System.out.println("Không có dữ liệu trong danh sách phần tử.");
        }
    }
}
