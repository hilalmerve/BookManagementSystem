1.	SPRİNG BOOT PROJESİNİN OLUŞTURULMASI
Daha öncesinde bilgisayarda Java ortamı yüklü olduğu için yükleme işlemlerine gerek kalmadan direk projeye başlandı. Gerekli depencies yani bağımlılıklar pom.xml dosyasına eklendikten sonra bir Spring Boot projesi oluşturuldu.

1.1.	 Eklenen Bağımlılıklar
-	Spring Data JPA: Veritabanı operasyonları gerçekleştirebilmek için kullanılan bağımlılıktır.
-	H2 Database: Proje için H2 Veritabanı kullanılması için gerekli bağımlılıktır.
-	ModelMapper: Java’da bir nesneden farklı bir nesneye mapleme işlemi yapılmasını sağlar.
-	Lombok: Java projesi geliştirirken daha temiz ve daha az kod yamak için IDE’ye entegre edilen anotasyon ile kod üretilmesini sağlayan kütüphanedir. Bazı işlemlerin daha kısa bir şekilde gerçekleştirilmesini sağlar.
-	Validation: Verilerin kontrol işlemlerinin yapılmasını sağlar. @NotBlank, @Email vb.
-	Diğer Bağımlılıklar: Bir Spring Boot projesinin çalışmasını sağlayan temel bağımlılıklardır.

2.	KİTAP YÖNETİM SİSTEMİ İÇİN ENTİTYLERİN BELİRLENMESİ VE OLUŞTURULMASI
Entity, veritabanından alınan veya veritabanına kaydedilen, üzerinde işlemler yapılan veritabanı nesnemizdir. Yapılacak işlemler temel olarak bir veritabanı üzerinde gerçekleşeceği için öncelikle entitylerin belirlenmesi ve daha sonra uygulanması gerekmektedir.

2.1.	 “BOOK” ENTİTY SINIFI
Proje; bir Kitap Yönetim Sistemi. Temel entity sınıfı “Book” sınıfıdır.



@Table anotasyonu ile veritabanında gözükecek olan tablonun ismi “books” olarak belirlendi.
@UniqueConstraint anotasyonu ile “name” sütunu unique bir şekilde belirlendi.
@Data anotasyonu lombok kütüphanesinden gelmektedir. Get, Set ve ToString metodlarının işlevlerini gerçekleştirir.
@NoArgsConstructor anotasyonu parametresiz constructor oluşturma işlevini gerçekleştirir.
@AllArgsConstructor anotasyonu tüm parametrelerin olduğu bir constructor oluşturma işlevini gerçekleştirir.
@Entity anotasyonu sınıfın bir veritabanı nesnesi yani bir entity olduğunu belirtir.
@Builder bir nesnenin sadece istenilen özelliklerinin diğer nesneye aktarılmasını sağlar.
@Id anotasyonu sınıfın anahtar değerini belirtir.
@GeneratedValue anotasyonu anahtar değerin nasıl oluşturulacağını belirtir. 4 çeşidi vardır. IDENTITY, AUTO, SEQUENCE ve TABLE.
GenerationType.IDENTITY: Otomatik olarak artan unique değer olan primary keyler oluşturur.
GenerationType.AUTO: Veritabanının kullandığı stratejiyi default olarak kullanır.
GenerationType.SEQUENCE: Primary key oluşturmak için veritabanı sıralamasını kullanır.
GenerationType.TABLE: Ayrı bir veritabanı tablosunun primary key değerlerini strateji olarak baz alır.
@Column anotasyonu sütun özelliklerinin belirlenmesini sağlar.
@ManytoOne anotasyonu bir varlığı başka bir varlikla bire çok ilişkisi olduğunu belirtir.
@JoinColumn anotasyonu bir varlığın içinde tanımlanan başka varlığın primary key’ini yani @Id etiketli alanını verilen isimde değişken olarak bu varlıkta tanımlanmasını sağlar ve tabloda gözükmesini sağlar.
@NotBlank anotasyonu özelliğin boş bırakılamayacağını belirtir.
Book Sınıfının id, name, author, category, publisher ve quantity özellikleri vardır.
Bir kitabın bir yazarı olabilir, bir yazarın birden fazla kitabı olabilir.
Bir kitabın bir yayınevi olabilir, bir yayınevinin birden fazla kitabı olabilir.
Bir kitabın bir kategorisi olabilir, bir kategoriye ait birden fazla kitap olabilir.
Şeklinde tanımlama yapılmıştır.

2.2.	 “AUTHOR” ENTITY SINIFI
 
Şekil 2.2. Author Sınıfı


2.3.	 “PUBLISHER” ENTITY SINIFI

 
Şekil 2.3. Publisher Sınıfı


2.4.	 “CATEGORY” ENTITY SINIFI

Şekil 2.4. Category Sınıfı


2.5.	 “USER” ENTITY SINIFI

 
Şekil 2.5. User Sınıfı

@OnetoOne anotasyonu varlıklar arasındaki birebir ilişkiyi tanımlar.
Bir kullanıcı bir ödünç alınanlar kısmına sahip olabilir, bir ödünç alınanlar sepetinin bir kullanıcısı olabilir.



2.6.	 “BORROWED” ENTITY SINIFI

 
Şekil 2.6. Borrowed Sınıfı

Borrowed sınıfı ödünç alınanların tutulup listelendiği sınıftır. Alışveriş sepeti gibi düşünülebilir. Ödünç alınan öğeler bu sınıfta liste halinde tutulur. Ödünç alınanlar sepeti kullanıcıya özeldir.
CascadeType iki nesne arasındaki ilişkinin davranışlarını belirler.
CascadeType.PERSIST: Kaydedilen nesnenin alt nesnesi de kaydedilir.
CascadeType.MERGE: Nesne merge edilirse ilişkili olan nesne de merge edilir.
CascadeType.REMOVE: Nesne silinirse ilişkili olan nesne de silinir.
CascadeType.REFRESH: Nesne yenilenirse bağlı olan nesne de silinir.
CascadeType.ALL: Yukarıdaki işlemler birlikte yapılır.



2.7.	 “BORROWEDITEM” ENTITY SINIFI


Şekil 2.7. BorrowedItemSsınıfı

3.	REPOSITORY’LERIN TANIMLANMASI
Veritabanında ekleme, silme, arama, güncelleme vb. gibi işlemlerin yapılması için tanımlanan interfacedir. JPARepository sınıfını extend eder. 

4.	REQUEST VE RESPONSE TANIMLARININ YAPILMASI
Request, istemciden sunucuya gönderilen istekleri tanımlar. Response, sunucudan istemciye dönen cevapları tanımlar. Proje içerisinde bu alanlar istenilene göre tanımlandı.

5.	H2 DATABASE KONFİGÜRASYONUNUN YAPILMASI
 
Şekil 5.1. application.properties dosyası

H2 Database üzerinde işlemler yapabilmek için application.properties dosyasında gerekli database ayarlarının yapılması gerekmektedir.

İlk olarak veritabanını oluşturmak için spring.jpa.hibernate.ddl-auto=create yapılması gerekmektedir.

Daha sonra update’e çevrilerek projede yapılan değişikliklerin veritabanı üzerinde güncellenmesi sağlanır.

Proje çalıştırılınca localhost:8085 portunda proje aktif hale gelir.

 
Şekil 5.2. localhost/h2-console


Şekil 5.3. H2 Veritabanında Oluşturulan bookdb Veritabanı

Oluşturulan entityler veritabanında tablolar halinde oluşturuldu.


6.	SERVİCE KISIMLARININ OLUŞTURULMASI
Projenin daha modüler halde olabilmesi için her görev ayrı ayrı yazılmaya çalışılmıştır. Service kısmının görevi asıl işi yapmaktır. Parametre olarak gelen isteği alıp veritabanı üzerinde gerekli işlemleri ve gerekli nesne dönüşümlerini yaparak bir cevap dönülmesini sağlar. Oluşturulan cevabın kullanıcıya gösterilmesi için cevabı Controller kısmına aktarır.


6.1.	 “AUTHORSERVİCE” SINIFI
 
Şekil 6.1. AuthorService Sınıfı


6.2.	 “PUBLİSHERSERVİCE” SINIFI

 
Şekil 6.3. PublisherService Sınıfı


 
Şekil 6.4. PublisherService Sınıfı Devamı


6.3.	“CATEGORYSERVİCE” SINIFI

 
Şekil 6.5. CategoryService Sınıfı


 
Şekil 6.6. CategoryService Sınıfı Devamı


6.4.	“USERSERVİCE” SINIFI
 
Şekil 6.7. UserService Sınıfı


Şekil 6.8. UserService Sınıfı Devamı



6.5.	 “BOOKSERVİCE” SINIFI

 
Şekil 6.9. BookService Sınıfı




 
Şekil 6.10. BookService Sınıfı


6.6.	 “BORROWEDSERVİCE” SINIFI
 
Şekil 6.11. BorrowedService Sınıfı


 
Şekil 6.12. BorrowedService Sınıfı Devamı-1



6.7.	“BORROWEDITEMSERVİCE” SINIFI

 
Şekil 6.15. BorrowedItemService Sınıfı




7.	CONTROLLER TANIMLAMALARININ YAPILMASI

7.1.	 “AUTHORCONTROLLER” SINIFI

 
Şekil 7.1. AuthorController Sınıfı



7.2.	“PUBLİSHERCONTROLLER” SINIFI

 
Şekil 7.2. PublisherController Sınıfı



7.3.	“CATEGORYCONTROLLER” SINIFI

 
Şekil 7.3. CategoryController Sınıfı



7.4.	“USERCONTROLLER” SINIFI

 
Şekil 7.4. UserController Sınıfı



7.5.	“BOOKCONTROLLER” SINIFI

 
Şekil 7.5. BookController Sınıfı



7.6.	“BORROWEDCONTROLLER” SINIFI

 
Şekil 7.6. BorrowedController Sınıfı



8.	“MAPPERCONFİG” SINIFI

 
Şekil 8.1. MapperConfig Sınıfı

@Configuration anotasyonu sınıfın bir konfigüsrasyon göreinde olduğunu belirtir.
@Bean anotasyonu Spring Framework uygulamamızın omurgasını oluşturan ve Spring IOC container tarafından yönetilen nesneleri tanımlamak için kullanılır. 

9.	PROGRAM ÇIKTILARI

9.1.	 ADD AUTHOR
 
9.2.	ADD PUBLISHER
 
9.3.	 ADD CATEGORY
 
9.4.	ADD USER

9.5.	ADD BOOK
 
9.6.	 UPDATE BOOK

9.7.	 GET ALL BOOKS
 
9.8.	GET BOOK BY ID

9.9.	DELETE BOOK
 
9.10.	ADD BORROWED
 

9.11.	ADD BORROWED ITEM
Ödünç alınanlar kısmına kitapların eklenmesi.
 

10.	PROJEDE KARŞILAŞILAN ZORLUKLAR VE ÇÖZÜM YOLLARI

10.1.	 H2 DATABASE KONFİGÜRASYON AYARLARI

Application.properties dosyasında H2 veritabanı konfigürasyonları yapılırken
spring.datasource.url=jdbc:h2:mem:/bookdb şeklinde bir tanımlama yapıldı. Fakat sonrasında veritabanı update modunda olmasına rağmen veritabanına kaydedilen veriler, uygulama her çalıştığında silindi.
spring.datasource.url=jdbc:h2:mem:/bookdb yerine spring.datasource.url=jdbc:h2:~/bookdb yazıldığında sorun çözüldü ve veritabanndaki veriler başarılı bir şekilde saklandı.

10.2.	@Autowired YAZILMADIĞINDA HATA ALINMASI
Sınıflarda Field Injection yaparken değişkenlerin üzerinde @Autowired yazılmadığında “initialization” hatası alındı. Değişkenler @Autowired ile tanımlandığında sorun çözüldü. 
@Autowired anotasyonu, bağımlılıkları otomatik olarak bir Spring bean'e bağlamak (enjekte etmek) için kullanılır. Otomatik bağımlılık enjeksiyonunu etkinleştirir. Yani, manuel yapılandırmaya gerek kalmadan gerekli bağımlılıkları otomatik olarak bulup enjekte eder.
