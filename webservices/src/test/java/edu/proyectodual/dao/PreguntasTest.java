package edu.proyectodual.dao;

/*
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;



import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import edu.proyectodual.model.dao.Preguntas;


@ExtendWith(MockitoExtension.class)
public class PreguntasTest {
    @Mock
    private ResultSet resultSet;

    @Test
    public void cityConstruction_ok() throws SQLException {
        Preguntas preguntaEsperada = Preguntas.builder()
                .pregunta("edificio mas alto del mundo")
                .respuesta("mi casa")
                .build();

        doReturn(preguntaEsperada.getPregunta()).when(resultSet).getString(any());
        when(resultSet.getString(any())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {

                if(invocationOnMock.getArgument(0).equals("pregunta")){
                    return preguntaEsperada.getPregunta();
                } else if(invocationOnMock.getArgument(0).equals("respuesta")) {
                    return preguntaEsperada.getRespuesta();
                }else{
                    return null;
                }
            }
        });

        Preguntas actualPregunta = new Preguntas(resultSet);

        MatcherAssert.assertThat(actualPregunta, Matchers.is(preguntaEsperada));

    }
    }
*/