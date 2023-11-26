import org.junit.Test;

import betterburrito.ObservableBurrito;
import betterburrito.CustomBurrito;
import betterburrito.VeggieBurrito;
import burrito.PortionSize;
import burrito.Protein;
import burrito.Size;
import burrito.Topping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * This class contains the tests for the classes within the betterburrito package.
 */
public class BetterBurritoTest {

  @Test
  public void testCustomBurritoBuilder() {
    ObservableBurrito customBurrito = new CustomBurrito.CustomBurritoBuilder()
            .size(Size.Normal)
            .addTopping(Topping.Cheese, PortionSize.Normal)
            .addTopping(Topping.MediumSalsa,PortionSize.Less)
            .addTopping(Topping.SourCream,PortionSize.Extra)
            .addProtein(Protein.Tofu, PortionSize.Normal)
            .build();
    assertEquals(5.9, customBurrito.cost(), 0.01);
  }

  @Test(expected = IllegalStateException.class)
  public void testNoSize() {
    new CustomBurrito.CustomBurritoBuilder().build();
  }

  @Test
  public void testAlacarteBurrito() {
    ObservableBurrito alacarteBurrito = new CustomBurrito.CustomBurritoBuilder()
            .size(Size.Normal)
            .addProtein(Protein.Tofu, PortionSize.Normal)
            .addTopping(Topping.Cheese, PortionSize.Normal)
            .addTopping(Topping.MediumSalsa, PortionSize.Less)
            .addTopping(Topping.SourCream, PortionSize.Extra)
            .build();
    assertEquals(5.9, alacarteBurrito.cost(), 0.01);
  }

  @Test
  public void testVeggieBurritoJumboSize() {
    ObservableBurrito veggieBurritoJumboSize = new VeggieBurrito.VeggieBurritoBuilder()
            .size(Size.Jumbo).build();
    assertEquals(7.2, veggieBurritoJumboSize.cost(), 0.01);
  }

  @Test
  public void testVeggieBurritoLessCheese() {
    ObservableBurrito veggieBurritoLessCheese = new VeggieBurrito.VeggieBurritoBuilder()
            .size(Size.Normal)
            .addTopping(Topping.Cheese, PortionSize.Less)
            .build();
    assertEquals(6.9, veggieBurritoLessCheese.cost(), 0.01);
  }

  @Test
  public void testVeggieBurritoBuilder() {
    ObservableBurrito veggieBurritoJumboSize =
            new VeggieBurrito.VeggieBurritoBuilder()
                    .size(Size.Jumbo)
                    .build();
    assertEquals(PortionSize.Normal, veggieBurritoJumboSize.hasProtein(Protein.BlackBeans));
    assertNull(veggieBurritoJumboSize.hasProtein(Protein.Chicken));
    assertEquals(PortionSize.Normal, veggieBurritoJumboSize.hasTopping(Topping.Cheese));
    assertNull(veggieBurritoJumboSize.hasTopping(Topping.Corn));
  }

  @Test
  public void testVeggieBurritoNormalNoGuac() {
    ObservableBurrito veggieBurritoJumboSize =
            new VeggieBurrito.VeggieBurritoBuilder()
                    .size(Size.Normal).noGuacamole().build();
    assertEquals(PortionSize.Normal, veggieBurritoJumboSize.hasProtein(Protein.BlackBeans));
    assertNull(veggieBurritoJumboSize.hasTopping(Topping.Guacamole));
  }



}
