# @RequestParam & @PathVariable

## @RequestParams

### Query String
Extract values from the string:
```
@GetMapping("/foos/{id}")
@ResponseBody
public String getFooById(@PathVariable String id) {
    return "ID: " + id;
}
```

Value will be mapped:

```
http://localhost:8080/spring-mvc-basics/foos/abc
----
ID: abc
```

### Encoded Value

@RequestParam is encoded.

```
http://localhost:8080/spring-mvc-basics/foos?id=ab+c
----
ID: ab c
```



## @PathVariable

### URI Path
```
@GetMapping("/foos")
@ResponseBody
public String getFooByIdUsingQueryParam(@RequestParam String id) {
    return "ID: " + id;
}
```

Response would be given:

```
http://localhost:8080/spring-mvc-basics/foos?id=abc
----
ID: abc
```

### Exact Value

@PathVariable is extracting values from the URI path, it’s not encoded

``` 
http://localhost:8080/spring-mvc-basics/foos?id=ab+c
----
ID: ab+c
```

## Similarity

### Optional

Both @RequestParam and @PathVariable can be optional.
```
@GetMapping({"/myfoos/optional", "/myfoos/optional/{id}"})
@ResponseBody
public String getFooByOptionalId(@PathVariable(required = false) String id){
      return "ID: " + id;
}
```
We can do either:
```
http://localhost:8080/spring-mvc-basics/myfoos/optional/abc
----
ID: abc
> ```
Or: 
```
http://localhost:8080/spring-mvc-basics/myfoos/optional
----
ID: null
```
Note that, **we should be careful when making @PathVariable optional, to avoid conflicts in paths**.


### URI
@RequestParam & @PathVariable are used to extract values from request URI (Uniform Resource Interface).
URI is a string that refers to a resource. Below is generic syntax of URI:
```
URI = scheme ":" ["//" authority] path ["?" query] ["#" fragment]
foo://example.com:8042/over/there?name=ferret#nose
\_/   \______________/\_________/ \_________/ \__/
 |           |            |            |        | 
scheme    authority      path        query   fragment
```
