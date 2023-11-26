package betterburrito;

import burrito.PortionSize;
import burrito.Protein;
import burrito.Topping;

/**
 * This class represents a veggie burrito. A veggie burrito has black beans,
 * medium salsa, cheese, lettuce, and guacamole, all in the regular portions.
 */
public class VeggieBurrito extends CustomBurrito {

  /**
   * Constructs a veggie burrito with the given VeggieBurritoBuilder.
   *
   * @param b the given builder to use when constructing the VeggieBurrito.
   */
  public VeggieBurrito(VeggieBurritoBuilder b) {
    this.size = b.size;
    this.proteins = b.proteins;
    this.toppings = b.toppings;
  }

  /**
   * This inner class represents a veggie burrito builder.
   * It extends the BurritoBuilder class, of which every object has a size, toppings, and proteins.
   */
  public static class VeggieBurritoBuilder
          extends BurritoBuilder<betterburrito.VeggieBurrito.VeggieBurritoBuilder> {

    /**
     * Constructs a custom burrito builder with the fields set as its superclass fields.
     * Its superclass has a null size, and a new hashmap for the toppings and protein.
     * Then black beans are added as a protein, as well as medium salsa, cheese, lettuce, guacamole
     * as toppings, all in normal portion sizes.
     */
    public VeggieBurritoBuilder() {
      super();
      this.proteins.put(Protein.BlackBeans, PortionSize.Normal);
      this.toppings.put(Topping.MediumSalsa, PortionSize.Normal);
      this.toppings.put(Topping.Cheese, PortionSize.Normal);
      this.toppings.put(Topping.Lettuce, PortionSize.Normal);
      this.toppings.put(Topping.Guacamole, PortionSize.Normal);
    }


    /**
     * Returns a veggie burrito builder with the cheese removed.
     *
     * @return a veggie burrito builder with the cheese removed
     */
    public VeggieBurritoBuilder noCheese() {
      this.toppings.remove(Topping.Cheese);
      return self();
    }

    /**
     * Returns a veggie burrito builder with the black beans removed.
     *
     * @return a veggie burrito builder with the black beans removed
     */
    public VeggieBurritoBuilder noBlackBeans() {
      this.proteins.remove(Protein.BlackBeans);
      return self();
    }

    /**
     * Returns a veggie burrito builder with the medium salsa removed.
     *
     * @return a veggie burrito builder with the medium salsa removed
     */
    public VeggieBurritoBuilder noMediumSalsa() {
      this.toppings.remove(Topping.MediumSalsa);
      return self();
    }

    /**
     * Returns a veggie burrito builder with the lettuce removed.
     *
     * @return a veggie burrito builder with the lettuce removed
     */
    public VeggieBurritoBuilder noLettuce() {
      this.toppings.remove(Topping.Lettuce);
      return self();
    }

    /**
     * Returns a veggie burrito builder with the guacamole removed.
     *
     * @return a veggie burrito builder with the guacamole removed
     */
    public VeggieBurritoBuilder noGuacamole() {
      this.toppings.remove(Topping.Guacamole);
      return self();
    }

    @Override
    protected VeggieBurritoBuilder self() {
      return returnBuilder();
    }

    @Override
    public ObservableBurrito build() throws IllegalStateException {
      if (this.size == null) {
        throw new IllegalStateException();
      }
      return new VeggieBurrito(returnBuilder());
    }

    @Override
    public VeggieBurritoBuilder returnBuilder() {
      return this;
    }
  }
}
