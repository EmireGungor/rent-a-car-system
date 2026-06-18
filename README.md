# rent-a-car-system
## Requirements

Bu projeyi derlemek ve çalıştırmak için Java JDK gereklidir. Proje Maven tabanlı olduğu için bağımlılık yönetimi ve derleme işlemleri Maven üzerinden yapılır.

Projede Maven yapılandırması Java 17 hedefiyle ayarlanmıştır. Geliştirme ortamında daha yeni bir JDK, örneğin Java 21, kullanılabilir; ancak proje Java 17 uyumluluğu hedeflenerek derlenir.

Maven kurulumunu kontrol etmek için:

`mvn -v`

Bu komut Maven sürüm bilgilerini gösteriyorsa kurulum başarılıdır. Eğer komut tanınmıyorsa Maven kurulu olmayabilir veya Maven'ın `bin` klasörü Windows `Path` değişkenine eklenmemiş olabilir.

Projenin başarıyla derlenip derlenmediğini kontrol etmek için proje klasöründe:

`mvn test`

Bu komut `BUILD SUCCESS` çıktısı veriyorsa proje Maven tarafından başarıyla derlenmiş ve test aşaması hata vermeden tamamlanmıştır.