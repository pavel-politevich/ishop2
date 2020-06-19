package by.lifetech.ishop.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="category")
public class Category implements Serializable {
    public static final long serialVersionUID = 4643347085351924131L;

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
    private int categoryId;
    
    @Column(name="category_name")
    private String name;
    
    @Column(name="category_desc")
    private String description;
    
    @OneToMany(mappedBy="itemCategory",
			   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH},
			   fetch = FetchType.EAGER)
    private List<Item> item;


    public Category() {}

    
    


    public int getCategoryId() {
		return categoryId;
	}





	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public String getDescription() {
		return description;
	}





	public void setDescription(String description) {
		this.description = description;
	}





	public List<Item> getItem() {
		return item;
	}





	public void setItem(List<Item> item) {
		this.item = item;
	}





	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryId == category.categoryId &&
                name.equals(category.name) &&
                Objects.equals(description, category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, name, description);
    }

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + ", description=" + description +"]";
	}

}
