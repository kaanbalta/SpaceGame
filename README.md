<br />
<div align="center">
  <a href="https://github.com/github_kullanici_adin/repo_adin">
    <img src="readme wp.png" alt="Logo" width="400" height="auto">
  </a>

  <h3 align="center">SpaceGame</h3>

  <p align="center">
    Java ile geliştirilmiş, veritabanı destekli bir uzay oyunu.
    <br />
    <a href="https://github.com/github_kullanici_adin/repo_adin"><strong>Dosyaları İncele »</strong></a>
    <br />
    <br />
    <a href="https://github.com/github_kullanici_adin/repo_adin/issues">Hata Bildir</a>
    ·
    <a href="https://github.com/github_kullanici_adin/repo_adin/issues">Özellik İste</a>
  </p>
</div>

<details>
  <summary>İçindekiler</summary>
  <ol>
    <li>
      <a href="#proje-hakkında">Proje Hakkında</a>
      <ul>
        <li><a href="#kullanılan-teknolojiler">Kullanılan Teknolojiler</a></li>
      </ul>
    </li>
    <li>
      <a href="#kurulum">Kurulum</a>
      <ul>
        <li><a href="#gereksinimler">Gereksinimler</a></li>
        <li><a href="#yükleme-adımları">Yükleme Adımları</a></li>
      </ul>
    </li>
    <li><a href="#oynanış">Oynanış</a></li>
    <li><a href="#iletişim">İletişim</a></li>
  </ol>
</details>

## Proje Hakkında

SpaceGame, Java kullanılarak geliştirilmiş klasik bir arcade oyunudur. Oyunun amacı, uzay gemisini kontrol ederek yukarıdan düşen meteorları vurmak ve en yüksek puanı toplamaktır.

Oyun, oyuncu verilerini ve skorlarını saklamak için bir MySQL veritabanı kullanır. Oyuncular kayıt olabilir, giriş yapabilir ve en yüksek skorlarını veritabanına kaydedebilirler.Skorlar oyun içinde bir records tablosunda tutulur.

### Kullanılan Teknolojiler

Bu proje aşağıdaki teknolojiler ve kütüphaneler kullanılarak geliştirilmiştir:

* ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
* ![Swing](https://img.shields.io/badge/Java_Swing-GUI-blue?style=for-the-badge)
* ![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
* ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
* ![JDBC](https://img.shields.io/badge/JDBC-Connector-red?style=for-the-badge)

## Kurulum

Projeyi yerel ortamınızda hatasız çalıştırmak için lütfen aşağıdaki adımları sırasıyla uygulayın.

### Gereksinimler

* **Java JDK** (Sürüm 8 veya üzeri)
* **XAMPP** (Apache ve MySQL servisi için)
* **Git**

### Yükleme Adımları

1.  **Projeyi Klonlayın**
    ```sh
    git clone [https://github.com/kaanbalta/SpaceGame.git](https://github.com/kaanbalta/SpaceGame.git)
    ```

2.  **Veritabanı Kurulumu (Kritik Adım!)**
    * XAMPP Control Panel'i açın, **Apache** ve **MySQL** servislerini başlatın.
    * Tarayıcınızda `http://localhost/phpmyadmin` adresine gidin.
    * Sol menüden **Yeni**'ye tıklayın ve veritabanı adı olarak tam olarak şunu yazın: **`demo`**
    * Oluşturduğunuz `demo` veritabanına tıklayın, üst menüden **İçe Aktar (Import)** sekmesine gelin.
    * Proje klasöründeki **`Spacegame.sql`** dosyasını seçin ve yükleyin.
    * *(Not: Veritabanı kullanıcı adı `root`, şifre ise `boş` olarak ayarlanmıştır. Standart XAMPP ayarlarıyla uyumludur.)*

3.  **Kütüphane Ayarı**
    * Projeyi IntelliJ IDEA (veya kullandığınız IDE) ile açın.
    * Proje ana dizininde bulunan **`mysql-connector-j-9.0.0.jar`** dosyasını projenin bağımlılıklarına (Dependencies/Libraries) ekleyin.
    * *IntelliJ IDEA için:* `File > Project Structure > Modules > Dependencies > + > JARs or Directories` yolunu izleyin.

4.  **Oyunu Başlatın**
    * `src` klasörü altındaki `Main` sınıfını çalıştırın.

## Oynanış

* **Hareket:** Uzay gemisini sağa ve sola yönlendirmek için **A ve D** tuşlarını kullanın.
* **Ateş Etme:** Meteorları vurmak için **SHIFT** tuşunu kullanın.
* **Amaç:** Canınız bitmeden mümkün olduğunca çok meteor vurarak en yüksek skoru elde edin.

## İletişim

Proje Linki: [https://github.com/kaanbalta/SpaceGame](https://github.com/kaanbalta/SpaceGame)
