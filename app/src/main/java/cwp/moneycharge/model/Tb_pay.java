package cwp.moneycharge.model;

public class Tb_pay {
//_id INTEGER  NOT NULL PRIMARY KEY,no not null integer AUTOINCREMENT ,money decimal,time varchar(10),"
		//+ "type integer  ,address varchar(100),mark varchar(200))
	
	private int _id;
	private int no;// �洢֧�����
	private double money;// �洢֧�����
	private String time;// �洢֧��ʱ��
	private int type;// �洢֧�����
	private String address;// �洢֧���ص�
	private String mark;// �洢֧����ע

	public Tb_pay() {
		// TODO Auto-generated constructor stub
		super();
	}
	public Tb_pay(int id,int no, double money, String time, int type,
			String address, String mark) {
		super();
		this._id=id;
		this.no = no;// Ϊ֧����Ÿ�ֵ
		this.money = money;// Ϊ֧����ֵ
		this.time = time;// Ϊ֧��ʱ�丳ֵ
		this.type = type;// Ϊ֧�����ֵ
		this.address = address;// Ϊ֧���ص㸳ֵ
		this.mark = mark;// Ϊ֧����ע��ֵ
	 
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getNo() {
		return no;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	
}