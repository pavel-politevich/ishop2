package by.lifetech.ishop.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "storage")
public class Storage implements Serializable {
	
	private static final long serialVersionUID = 890213113243688572L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id")
	private Item item;
	
	@Column(name = "count")
	private int count;
	
	public Storage( ) {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Item getItems() {
		return item;
	}

	public void setItems(Item item) {
		this.item = item;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + id;
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
		Storage other = (Storage) obj;
		if (count != other.count)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Storage [id=" + id + ", count=" + count + "]";
	}

}
