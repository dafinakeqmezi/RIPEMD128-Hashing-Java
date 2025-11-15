# RIPEMD128-Hashing-Java

Ky projekt demonstron përdorimin e algoritmit të hashimit RIPEMD-128 në Java.

## Si funksionon
- Merr një tekst nga përdoruesi
- Gjeneron hash-in RIPEMD128
- Shfaq rezultatin në format hexadecimal

## Si ta kompilosh dhe ekzekutosh

### Kompilim
```bash
javac -d out -sourcepath src/main/java src/main/java/ripemd128/RIPEMD128.java
```

### Ekzekutim
```bash
java -cp out ripemd128.RIPEMD128
```

### Ekzekutim me tekst personalizuar
```bash
java -cp out ripemd128.RIPEMD128 "Teksti juaj këtu"
```

## Shembull
Input: `Siguria e informacionit`  
Output: `b0213b834c9700d5e0480b171990cb7b`
