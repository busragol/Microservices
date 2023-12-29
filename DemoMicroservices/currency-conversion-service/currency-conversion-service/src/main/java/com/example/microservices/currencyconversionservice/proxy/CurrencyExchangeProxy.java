package com.example.microservices.currencyconversionservice.proxy;

//CurrencyExchange service ini çağırabilmek için CurrencyConversion içerisinde böyle bir proxy yazmalıyız
//Feign

import com.example.microservices.currencyconversionservice.bean.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Asagıdaki name e girilen deger service in app.prop una eklediğimiz
// application name den geliyor, hangi service i çağıracağını buradan anlıyor
//@FeignClient(name = "currency-exchange", url = "localhost:8000")

@FeignClient(name = "currency-exchange") //bu service in birden fazla instance olabilir
// ve hepsinin url ini buraya yazmak dogru bir çözüm değildir, bunun yerine
//sadece name yazıp load balancing yapmak daha doğrudur

//url yazmasak da feign client eureka server sayesinde currency exchange service in instane ını bulabiliyor
public interface CurrencyExchangeProxy {

    //CurrencyExchange service deki kullanmak istediğimiz methodu buraya ekliyoruz.
    //return type ı bu projedekı maplemek istediğimiz tipi seçiyoruz.
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
