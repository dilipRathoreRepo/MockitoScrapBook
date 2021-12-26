package com.diliprathore.mockito.scrapbook;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;


public class ATest {
    @Mock
    B b;
    private A a;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        a = new A(b);
    }

    @Test
    public void usesVoidMethod_shouldCallVoidMethod() throws Exception {
//        when clause is not needed when the method returns a NULL OR use doNothing().when()
        doNothing().when(b).voidMethod();
        assertSame(1, a.usesVoidMethod());
        verify(b).voidMethod();
    }

    @Test(expected = RuntimeException.class)
    public void usesVoidMethod_shouldRaiseException() throws Exception {
        doThrow(Exception.class).when(b).voidMethod();
        a.usesVoidMethod();
        verify(b).voidMethod();
    }

    @Test(expected = RuntimeException.class)
    public void test_consecutiveCalls() throws Exception {
        doNothing().doThrow(Exception.class).when(b).voidMethod(); // Stubbing of chaining of same method calls output
        assertSame(1, a.usesVoidMethod());
        a.usesVoidMethod();
        verify(b, times(2)).voidMethod();
    }

}
