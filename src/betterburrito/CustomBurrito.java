package betterburrito;

import java.util.Map;

import burrito.PortionSize;
import burrito.Protein;
import burrito.Size;
import burrito.Topping;

/**
 * This class represents a custom burrito that can have an arbitrary number
 * of proteins and toppings, both of arbitrary portion sizes.
 */
public class CustomBurrito implements ObservableBurrito {
  protected Size size;
  protected Map<Protein,PortionSize> proteins;
  protected Map<Topping, PortionSize> toppings;

  /**
   * Constructs a custom burrito with a null size, and a Hashmap
   * created through a CustomBurritoBuilder for its proteins and toppings.
   */
  protected CustomBurrito() {
    this.size = null;
    this.proteins = new CustomBurritoBuilder().proteins;
    this.toppings = new CustomBurritoBuilder().toppings;
  }

  /**
   * Constructs a custom burrito with the given CustomBurritoBuilder.
   *
   * @param b the given builder to use when constructing the CustomBurrito.
   */
  private CustomBurrito(CustomBurritoBuilder b) {
    this.size = b.size;
    this.proteins = b.proteins;
    this.toppings = b.toppings;
  }

  /**
   * This inner class represents a custom burrito builder.
   * It extends the BurritoBuilder class, of which every object has a size, toppings, and proteins.
   */
  public static class CustomBurritoBuilder
          extends BurritoBuilder<betterburrito.CustomBurrito.CustomBurritoBuilder> {

    /**
     * Constructs a custom burrito builder with the fields set as its superclass fields.
     * Its superclass has a null size, and a new hashmap for the toppings and protein.
     */
    public CustomBurritoBuilder() {
      super();
    }

    /**
     * Returns a burrito builder with an added protein of the given protein type and portion size.
     *
     * @param p the type of protein to be added
     * @param s the portion size that the protein is set to
     * @return a burrito builder with the updated protein
     */
    public CustomBurritoBuilder addProtein(Protein p, PortionSize s) {
      this.proteins.put(p, s);
      return self();
    }

    @Override
    protected CustomBurritoBuilder self() {
      return returnBuilder();
    }

    @Override
    public CustomBurrito build() throws IllegalStateException {
      if (this.size == null) {
        throw new IllegalStateException();
      }
      return new CustomBurrito(returnBuilder());
    }

    @Override
    public CustomBurritoBuilder returnBuilder() {
      return this;
    }
  }

  @Override
  public PortionSize hasTopping(Topping name) {
    return this.toppings.getOrDefault(name,null);
  }

  @Override
  public PortionSize hasProtein(Protein name) {
    return this.proteins.getOrDefault(name,null);
  }

  @Override
  public double cost() {
    double cost = 0.0;
    for (Map.Entry<Protein, PortionSize> item : this.proteins.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultipler();
    }

    for (Map.Entry<Topping, PortionSize> item : this.toppings.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultipler();
    }
    return cost + this.size.getBaseCost();
  }
}
