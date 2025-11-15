# RIPEMD128-Hashing-Java

Ky projekt demonstron përdorimin e algoritmit të hashimit RIPEMD-128 në Java.

# Përshkrim

**RIPEMD-128** është një funksion hash kriptografik që prodhon një **vlerë hash 128-bit (16 bytes)**. Ai është dizajnuar si pjesë e familjes së funksioneve hash RIPEMD si një alternativë ndaj MD4 dhe MD5. RIPEMD-128 përdoret kryesisht për:

- Verifikimin e integritetit të të dhënave  
- Nënshkrimet digjitale  

Duke ofruar një mënyrë të sigurt dhe të shpejtë për të hash-uar mesazhet.

## Karakteristikat kryesore të RIPEMD-128:

- Prodhon një output hash 128-bit.  
- E projektuar për të rezistuar më mirë ndaj collison attacks krahasuar me MD4/MD5.  
- Përdoret gjerësisht në aplikime kriptografike ku pranohet një madhësi hash më e vogël.


## Versionet
Ky projekt përfshin **dy implementime të RIPEMD-128** në Java:

### 1. “From Scratch”

- Implementon algoritmin **RIPEMD-128** direkt në Java, pa përdorur librari, tregon logjikën e algoritmit në nivel bazik dhe e gjithë logjika e hash-it është e shkruar manualisht.

```javac src/main/java/ripemd128/RIPEMD128.java```
```java -cp out ripemd128.RIPEMD128```

### 2. BouncyCastle

- Përdor bibliotekën BouncyCastle, e cila ofron implementime të sigurta dhe të optimizuara për hash funksione, përfshirë RIPEMD-128.

```javac src/main/java/bouncycastle/RIPEMD128Test.java```
```java -cp out ripemd128Test.RIPEMD128Test```

## Struktura e Projektit

```
RIPEMD128-Hashing-Java/
├── src/
│   └── main/
│       └── java/
│           └── ripemd128/        #Versioni 1
│               └── ripemd128.java
            └── bouncycastle/     #Versioni 2
│               └── ripemd128Test.java      
└── README.md                          
```


## Autor

Ardi Berdyna,
Dafina Keqmezi,
Ylljeta Kicaj

