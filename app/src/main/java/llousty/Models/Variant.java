package llousty.Models;

import javafx.scene.image.ImageView;

public class Variant extends Model{
    private String variantName;
    private int productId;
    private ImageView variantPhoto;
    public Variant(int id, String variantName, int productId, ImageView variantPhoto) {
        super(id);
        this.variantName = variantName;
        this.productId = productId;
        this.variantPhoto = variantPhoto;
    }
    public String getVariantName() {
        return variantName;
    }
    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public ImageView getVariantPhoto() {
        return variantPhoto;
    }
    public void setVariantPhoto(ImageView variantPhoto) {
        this.variantPhoto = variantPhoto;
    }
    
}