# RIPEMD128-Hashing-Java

Ky projekt demonstron përdorimin e algoritmit të hashimit RIPEMD-128 në Java.

## Përshkrim

RIPEMD-128 është një algoritëm hash kriptografik që prodhon një hash 128-bit (16 bytes) që përfaqësohet si një string hexadecimal me 32 karaktere. Ky projekt implementon algoritmin RIPEMD-128 nga e para (scratch) duke përdorur Java.

## Kërkesat

- Java JDK 8 ose më i lartë
- Java kompajler (javac)

## Si funksionon

- Merr një tekst nga përdoruesi (si string ose si byte array)
- E pad-at mesazhin sipas specifikacioneve të RIPEMD-128
- Përpunon mesazhin në blloqe 512-bit
- Gjeneron hash-in RIPEMD-128 përmes algoritmit dual-pipeline
- Shfaq rezultatin në format hexadecimal

## Struktura e Projektit

```
RIPEMD128-Hashing-Java/
├── src/
│   └── main/
│       └── java/
│           └── ripemd128/
│               └── RIPEMD128.java    # Implementimi i algoritmit
├── out/                               # Folder për file-et e kompiluara (.class)
└── README.md                          # Ky dokument
```

## Si ta kompilosh dhe ekzekutosh

### Kompilim
```bash
javac -d out -sourcepath src/main/java src/main/java/ripemd128/RIPEMD128.java
```

Kjo krijon folderin `out/` dhe vendos file-et `.class` të kompiluara atje.

### Ekzekutim

**Test default (me mesazhe të paracaktuar):**
```bash
java -cp out ripemd128.RIPEMD128
```

**Me tekst personalizuar:**
```bash
java -cp out ripemd128.RIPEMD128 "Teksti juaj këtu"
```

**Me shumë argumente:**
```bash
java -cp out ripemd128.RIPEMD128 "argument1" "argument2"
```

## Shembuj

### Shembull 1: Mesazh i thjeshtë
```
Input:  Siguria e informacionit
Output: b0213b834c9700d5e0480b171990cb7b
```

### Shembull 2: String i zbrazët
```
Input:  (empty)
Output: 66467f456d88153c09b776d3865bcc16
```

### Shembull 3: Karakter i vetëm
```
Input:  a
Output: 96dd8f0c942274a99ccd1360b3770dda
```

### Shembull 4: Tekst i shkurtër
```
Input:  abc
Output: 211b03dabe5fdcb8a2d50723ffa0a02c
```

## Karakteristikat

- Implementim i plotë i RIPEMD-128 nga e para
- Mbështetje për UTF-8 encoding
- Përpunon mesazhe të gjatësisë arbitrare
- Output në format hexadecimal
- Testim dhe demonstrim integruar

## Si të përdoret në kod

```java
import ripemd128.RIPEMD128;

RIPEMD128 ripemd128 = new RIPEMD128();

String hash1 = ripemd128.hash("Mesazhi juaj");

byte[] message = "Mesazh".getBytes();
String hash2 = ripemd128.hash(message);
```

## Autor

Ardi Berdyna
Dafina Keqmezi
Ylljeta Kicaj

