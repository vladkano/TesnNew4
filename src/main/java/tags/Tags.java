package tags;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Tags extends Base {

    static String tags = "";
    static String itemName = "";
    By tag = By.xpath("//b[@class='tag-list__tag']");

    public Tags(WebDriver driver) {
        super(driver);
    }

    public String getTag() {
        return driver.findElement(tag).getText();
    }

    public String getRingsTag() {
        List<WebElement> elements = driver.findElements(tag);
        return elements.get(0).getAttribute("textContent");
    }

    public String getEarringsTag() {
        List<WebElement> elements = driver.findElements(tag);
        return elements.get(0).getAttribute("textContent");
    }

    public String getBroshiTag() {
        List<WebElement> elements = driver.findElements(tag);
        return elements.get(0).getAttribute("textContent");
    }

    public String getNecklacesTag() {
        List<WebElement> elements = driver.findElements(tag);
        return elements.get(0).getAttribute("textContent");
    }


    public String nameEarringsTags() {
        String query = "SELECT item_tag.name from item_sku " +
                "JOIN item ON item.id = item_sku.item_id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item.id = item_picture_list.item_id and (item_picture_list.tag_id = 1 or item_picture_list.tag_id = 4)) " +
                "and catalog_id=1 and is_archive = 0 and price != 0 and section = 'catalog' and subsection = 'sergi' " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position LIMIT 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tags = resultSet.getString("name");
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(name);
        return tags;
    }

    public String nameBroshiTags() {
        String query = "SELECT item_tag.name from item_sku " +
                "JOIN item ON item.id = item_sku.item_id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item.id = item_picture_list.item_id and (item_picture_list.tag_id = 1 or item_picture_list.tag_id = 4)) " +
                "and catalog_id=4 and is_archive = 0 and price != 0 and section = 'catalog' and subsection = 'broshi' " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position LIMIT 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tags = resultSet.getString("name");
//                System.out.println(tags);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(name);
        return tags;
    }

    public String nameOfRingTags() {
        String query = "SELECT item_tag.name from item_sku " +
                "JOIN item ON item.id = item_sku.item_id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item.id = item_picture_list.item_id and (item_picture_list.tag_id = 1 or item_picture_list.tag_id = 4)) " +
                "and catalog_id=5 and is_archive = 0 and price != 0 and section = 'catalog' and subsection = 'koltsa' " +
                "and item_sku.url is not null and balance > 0 " +

                "group by item_catalog_position.position LIMIT 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tags = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(name);
        return tags;
    }

    public String nameNecklacesTags() {
        String query = "SELECT item_tag.name from item_sku " +
                "JOIN item ON item.id = item_sku.item_id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item.id = item_picture_list.item_id and (item_picture_list.tag_id = 1 or item_picture_list.tag_id = 4)) " +
                "and catalog_id=2 and is_archive = 0 and price != 0 and section = 'catalog' and subsection = 'kole' " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position LIMIT 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tags = resultSet.getString("name");
//                System.out.println(tags);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }


    public static void main(String[] args) {
        String query = "SELECT item_tag.name from item_sku " +
                "JOIN item ON item.id = item_sku.item_id " +
                "JOIN item_tag_list ON item.id = item_tag_list.item_id " +
                "JOIN item_tag ON item_tag_list.tag_id = item_tag.id " +
                "JOIN item_catalog_position ON item.id = item_catalog_position.item_id " +
                "JOIN designer ON item.designer_id = designer.id " +
                "JOIN catalog ON item.catalog_id = catalog.id " +
                "JOIN item_picture_list ON item.id = item_picture_list.item_id " +
                "JOIN storage_stock ON item_sku.id = storage_stock.sku_id " +
                "where EXISTS (SELECT * FROM item_sku WHERE item.id = item_picture_list.item_id and (item_picture_list.tag_id = 1 or item_picture_list.tag_id = 4)) " +
                "and catalog_id=2 and is_archive = 0 and price != 0 and section = 'catalog' and subsection = 'kole' " +
                "and item_sku.url is not null and balance > 0 " +
                "group by item_catalog_position.position LIMIT 1";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                tags = resultSet.getString("name");
                System.out.println(tags);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worker.getSession().disconnect();

    }

}
