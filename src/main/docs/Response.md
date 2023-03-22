# Response Return

## HttpServletResponse


### Pros:

Belongs to the Servlet technology which is not limited to the HTTP protocol.

Able to set cookies on the response with addCookie() method.

Full low level of access. 

### Cons:

Does not have message method which means we must customize it manually.

It is an interface so that we may override some methods.


### Use Case:

For identifying and tracking user APIs with cookies.


## ResponseEntity

### Pros:

Belongs to Spring Framework, within a @Controller class.</p>

It is a class which is convenient to use its methods.


### Cons:

Does not have methods regarding cookies that we should implement it if we want to use.

Provide more verbosity.

### Use Case:

For APIs not related to cookies.

> Conclusion: Both of two methods return header, HTTP status code and body response but not the message. 
We should implement manually if we also want to return messages in response.