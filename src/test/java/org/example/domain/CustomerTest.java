package org.example.domain;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;


/**
 * When running tests in the IDE install the "Enhancement plugin".
 * <p>
 * http://ebean-orm.github.io/docs/setup/enhancement#ide
 */
public class CustomerTest {

  Customer rob;
  /**
   * Get the "default server" and save().
   */
  @Test
  public void insert_via_server() {

    rob = new Customer("Rob");
    List<Bill> bills = new ArrayList<Bill>();
    Bill bill1 = new Bill();
    Bill bill2 = new Bill();
    bill1.setItem("test");
    bill2.setItem("test");
    bills.add(bill1);
    bills.add(bill2);
    rob.setBills(bills);

    EbeanServer server = Ebean.getDefaultServer();
    server.save(rob);

    assertNotNull(rob.getId());
  }

  /**
   * Use the Ebean singleton (effectively using the "default server").
   */
  @Test(dependsOnMethods = "insert_via_server")
  public void insert_via_ebean() {

    Customer jim = new Customer("Jim");
    Ebean.save(jim);

    assertNotNull(jim.getId());
  }


  /**
   * Find and then update.
   */
  @Test(dependsOnMethods = "insert_via_server")
  public void updateRob() {

    Customer rob = Ebean.find(Customer.class)
        .where().eq("name", "Rob")
        .findUnique();

    rob.setNotes("Doing an update");
    Ebean.save(rob);
  }
  
  @Test(dependsOnMethods = "insert_via_server")
  public void testBills() {

    Customer rob = Ebean.find(Customer.class)
        .where().eq("name", "Rob")
        .findUnique();
    
    List<Bill> bills = rob.getBills();
    assertEquals(bills.size(), 2);

  }

  /**
   * Execute an update without a prior query.
   */
  @Test(dependsOnMethods = "updateRob")
  public void statelessUpdate() {

    Customer upd = new Customer();
    upd.setId(rob.getId());
    upd.setNotes("Update without a fetch");

    Ebean.update(upd);
  }

}