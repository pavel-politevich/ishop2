package by.lifetech.ishop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="items")
public class Item implements Serializable {
    public static final long serialVersionUID = 3650830057666549258L;

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
    private int itemId;
       
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="id_category")
    private Category itemCategory;
    
    @Column(name="name_short")
    private String nameShort;
    
    @Column(name="name_full")
    private String nameFull;
    
    @Column(name="description")
    private String description;
    
    @Column(name="manufacturer")
    private String manufacturer;
    
    @Column(name="price")
    private BigDecimal price;
       
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="state_id")
    private ItemState itemState;
    
    @OneToMany(mappedBy="item",
			   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH},
			   fetch = FetchType.EAGER)
    private List<Review> itemReviews;
    
    @OneToOne(mappedBy = "item", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
    private Storage itemStorage;
    

    
    
    @OneToMany(mappedBy="item",
			   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	private List<OrderDetail> orderDetails;


    


	public Item () {}


	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public Category getItemCategory() {
		return itemCategory;
	}


	public void setItemCategory(Category itemCategory) {
		this.itemCategory = itemCategory;
	}


	public String getNameShort() {
		return nameShort;
	}


	public void setNameShort(String nameShort) {
		this.nameShort = nameShort;
	}


	public String getNameFull() {
		return nameFull;
	}


	public void setNameFull(String nameFull) {
		this.nameFull = nameFull;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public ItemState getItemState() {
		return itemState;
	}


	public void setItemState(ItemState itemState) {
		this.itemState = itemState;
	}


	public List<Review> getItemReviews() {
		return itemReviews;
	}


	public void setItemReviews(List<Review> itemReviews) {
		this.itemReviews = itemReviews;
	}


	public Storage getItemStorage() {
		return itemStorage;
	}


	public void setItemStorage(Storage itemStorage) {
		this.itemStorage = itemStorage;
	}
	


	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}


	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + itemId;
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((nameFull == null) ? 0 : nameFull.hashCode());
		result = prime * result + ((nameShort == null) ? 0 : nameShort.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (itemId != other.itemId)
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (nameFull == null) {
			if (other.nameFull != null)
				return false;
		} else if (!nameFull.equals(other.nameFull))
			return false;
		if (nameShort == null) {
			if (other.nameShort != null)
				return false;
		} else if (!nameShort.equals(other.nameShort))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemCategory=" + itemCategory + ", nameShort=" + nameShort + ", nameFull="
				+ nameFull + ", description=" + description + ", manufacturer=" + manufacturer + ", price=" + price
				+ ", itemState=" + itemState + "]";
	}


	
	
	

}
