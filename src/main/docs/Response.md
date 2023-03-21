# Response Return

## HttpServletResponse


### Pros

<p> Belongs to the Servlet technology which is not limited to the HTTP protocol.</p>

<p> Able to set cookies on the response with addCookie() method.</p>

<p> Full low level of access. </p>

### Cons

<p> Does not have message method which means we must customize it manually.
</p>

<p> It is an interface so that we may should overriding some methods.
</p>

### Use Case

For identifying and tracking user APIs with cookies.


## ResponseEntity

### Pros

<p> Belongs to Spring Framework, within a @Controller class.</p>

<p> It is a class which is convenient to use its methods.

</p>

### Cons

<p> Does not have methods regarding cookies that we should implement it if we want to use.
</p>

<p> Provide more verbosity.
</p>

### Use Case

For APIs not related to cookies.

> Conclusion: Both of two methods return header, HTTP status code and body response but not the message. 
We should implement manually if we also want to return messages in response.