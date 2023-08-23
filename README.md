
# Conference Planner

Belirli süreye sahip sunumları alır ve aşağıdaki şartlara göre bir konferans planlar:
- Sunumlar sabah ve öğleden sonra yapılacaktır
- Sunumlar arası mola bulunmamaktadır
- Sunum süreleri dakika veya 'ligtning' (5dk) olarak belirtilir
- Sabah sunumlar 9:00'da başlar ve 12:00'da biter
- Saat 12:00'da öğle yemeği olacaktır
- Öğleden sonra sunumlar 13:00'da başlar ve iletişim etkinliklerine (networking event) kadar devam eder, eğer iletişim etkinliği yoksa 17:00'da biter
- İletişim etkinliği sunumlardan zaman kalırsa yapılır, 16:00'dan önce başlayamaz ve en geç 17:00'da biter

### Tech-Stack
- Spring Boot
- H2 Database
- Maven
- Swagger

### Nasıl Çalışır?
Problem ilk okunduğunda akla memory allocation algoritmaları gelir: Belirli bir alanımız var ve elimizdeki sınırlı alana sahip verileri bu belirli alana minimum boşlukla (fragmentation) sığdırmamız gerek.

Problemi sadeleştirmek için veri yapımızı aşağıdaki şekilde modelleyebiliriz:

| Class            | Açıklama                                                                                                |
|------------------|---------------------------------------------------------------------------------------------------------|
| `Presentation` | Tek bir sunumu ifade eder. İsim ve süre bilgisi tutar.                                                  |
| `Session`        | Öğleden önce ve sonraki oturumları ifade eder. Sunum listesi ve belirli bir süre kapasitesine sahiptir. |
| `ConferenceDay`  | Konferansın bir gününü ifade eder. Gün ve 2 adet Session bilgisine sahiptir.                            |
| `Conference`  | Konferans planını ifade eder. Konferans günleri listesine sahiptir.                                     |

Bu veri yapımıza göre şartlarımızı tekrar yazalım:
- Her sunumun süresi dakika cinsindendir
- Bir konferans günü 2 adet oturumdan oluşur; sabah oturumu 180dk (3sa) ve akşam oturumu 240dk'dır (4sa)
- Sunumlar sabah oturumuna tam sığmak zorunda, mola yok
- Öğleden sonraki oturum boşluklara izin vermekte, çünkü opsiyonel bir Networking Event var

Verilen şartlara ve veri yapımıza göre planlama algoritmamız şu şekildedir:
1. Sunumların sürelerini topla ve gerekli gün sayısını bularak konferans günlerini oluştur
2. Tam yerleştirmeyi sağlamak için; sunumları, öğleden önceki oturumlara yer kalmayıncaya kadar best-fit algoritmasına göre yerleştir
3. Kalan sunumları öğleden sonraki oturumlara yine best-fit algoritmasına göre yerleştir

Bu işlemler sonucunda tüm sunumlarımız konferans günlerimizin oturumlarına yerleştirilmiş olur, 
ancak minimum düzeyde boşluklar ('fragmentation') da oluşacaktır. Bu boşlukları yok etmek için bazı sunumların yerlerinin 
değiştirilmesi işlemi (relocation) yapılmalıdır.

Relocation işleminde sunumlar yalnızca kendi günleri içerisinde ve oturumlar arası aşağıdaki şekilde yapılır:
1. İlk oturumdaki sunum listesi alınır ve artan sıralanır
2. İkinci oturumdaki sunum listesi alınır ve artan sıralanır
3. İlk oturumdaki sunum listesinden elemanlar sırayla alınır ve ikinci oturum listesindekilerle aşağıdaki şart sağlanana kadar karşılaştırılır
   - Sunum süresi (I.oturum sunumu) + boşluk miktarı = sunum süresi (II.oturum sunumu)
4. Şart sağlandığında sunumlar yer değiştirilir

Bu işlem sonucunda ilk oturumlar boşluksuz yerleştirilmiş olur. İkinci oturumdaki boşluklar ise, boşluk miktarınca 
(maksimum 60dk olmak koşuluyla) 'Networking Event' eklenerek giderilmiş olur.

> Ek olarak, daha net bir yerleştirme için; sunumlar azalan sırada sıralanıp best-fit algoritmasına göre yerleştirilebilir.
Sonrasında, oturumlar arasına ilaveten günler arası relocation işlemi de yapılabilir. Ancak benim vaktim kısıtlı :)
