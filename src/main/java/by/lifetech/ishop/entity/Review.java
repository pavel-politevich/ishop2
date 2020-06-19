package by.lifetech.ishop.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "item_reviews")
public class Review implements Serializable {
	
    public static final long serialVersionUID = 9094335382261950240L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
    
    @Column(name = "event_date")
    private Date reviewDate;
    
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;
    
    @Column(name = "rate")
    private byte rate;
    
    @Column(name = "text")
    private String comment;
    
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="item_id")
    private Item item;
    

    public Review() {}


	public Date getReviewDate() {
		return reviewDate;
	}


	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public byte getRate() {
		return rate;
	}


	public void setRate(byte rate) {
		this.rate = rate;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + rate;
		result = prime * result + ((reviewDate == null) ? 0 : reviewDate.hashCode());
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
		Review other = (Review) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (rate != other.rate)
			return false;
		if (reviewDate == null) {
			if (other.reviewDate != null)
				return false;
		} else if (!reviewDate.equals(other.reviewDate))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Review [reviewDate=" + reviewDate + ", rate=" + rate + ", comment=" + comment + "]";
	}


	

}
