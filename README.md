# 🧾 Exercício — Sistema de Pontuação de Clientes (CustomerScoreService)

## 🎯 Objetivo
Este exercício tem como objetivo praticar **testes unitários com JUnit 5** em um cenário de negócio próximo da realidade, **sem uso de mocks**.  
Você irá testar um serviço que gerencia a **pontuação de fidelidade dos clientes** de uma empresa.

---

## 🧩 Contexto
A empresa possui um programa de fidelidade em que os clientes ganham e perdem pontos com base em suas ações:

- Ao realizar compras → **ganham pontos**
- Ao cancelar pedidos → **perdem pontos**
- Com o tempo de fidelidade → **ganham bônus**

O serviço precisa garantir que:
- Nenhum cliente tenha pontuação negativa.  
- Pontos adicionados ou removidos sejam sempre valores válidos (> 0).  
- Bônus sejam aplicados corretamente conforme o tempo de fidelidade.

---

## ⚙️ Classe: `Customer.java`
Representa o cliente e mantém o estado atual da sua pontuação.

```java
public class Customer {

    private String name;
    private int score;

    public Customer(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() { return name; }

    public int getScore() { return score; }

    public void setScore(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }
        this.score = score;
    }
}
```

---

## 💼 Classe: `CustomerScoreService.java`

```java
public class CustomerScoreService {

    public void addPoints(Customer customer, int points) {
        if (points <= 0) throw new IllegalArgumentException("Points to add must be greater than zero");
        customer.setScore(customer.getScore() + points);
    }

    public void removePoints(Customer customer, int points) {
        if (points <= 0) throw new IllegalArgumentException("Points to remove must be greater than zero");
        if (points > customer.getScore()) throw new IllegalArgumentException("Cannot remove more points than the current score");
        customer.setScore(customer.getScore() - points);
    }

    public void applyLoyaltyBonus(Customer customer, int yearsAsCustomer) {
        if (yearsAsCustomer < 0) throw new IllegalArgumentException("Years as customer cannot be negative");

        int bonus = 0;
        if (yearsAsCustomer >= 1 && yearsAsCustomer <= 3) bonus = 50;
        else if (yearsAsCustomer >= 4 && yearsAsCustomer <= 6) bonus = 100;
        else if (yearsAsCustomer > 6) bonus = 200;

        customer.setScore(customer.getScore() + bonus);
    }
}
```

---

## 🧪 Classe de Teste: `CustomerScoreServiceTest.java`

Seu objetivo é implementar os testes abaixo usando **JUnit 5**, seguindo o padrão **Cenário → Execução → Validação (Given / When / Then)**.

```java
class CustomerScoreServiceTest {

    private final CustomerScoreService service = new CustomerScoreService();

    @Test
    void shouldAddPointsToCustomerCorrectly() { }

    @Test
    void shouldRemovePointsFromCustomerCorrectly() { }

    @Test
    void shouldApplyBonusForCustomerWithThreeYears() { }

    @Test
    void shouldApplyBonusForCustomerWithSevenYears() { }

    @Test
    void shouldThrowExceptionWhenAddingZeroPoints() { }

    @Test
    void shouldThrowExceptionWhenRemovingMorePointsThanCustomerHas() { }

    @Test
    void shouldThrowExceptionWhenYearsAsCustomerIsNegative() { }

    @Test
    void shouldNotAllowNegativeScoreViaSetter() { }

}
```


## 🧠 Dicas para os testes

- Use `assertEquals` para validar valores esperados do score.  
- Use `assertThrows` para testar exceções.  
- Lembre-se de inicializar novos objetos `Customer` em cada teste.  
- Dê nomes descritivos aos testes (ex: `shouldApplyLoyaltyBonusAfterAddingPoints`).  
- Siga o padrão:
  ```java
  // Cenário
  Customer customer = new Customer("Alice");

  // Execução
  service.addPoints(customer, 100);

  // Ação
  assertEquals(100, customer.getScore());
  ```