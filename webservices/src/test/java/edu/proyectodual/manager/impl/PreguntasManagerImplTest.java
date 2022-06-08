/*package edu.proyectodual.manager.impl;


import edu.proyectodual.model.dao.Preguntas;
import edu.proyectodual.model.manager.impl.PreguntasManagerImpl;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PreguntasManagerImplTest {

    @Mock
    private Connection connection;

    @Mock
    private Statement statement;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @Mock
    private String letra;

    @InjectMocks
    private PreguntasManagerImpl preguntasManager;

    @Test
    void respuestasCon_ok() throws SQLException {

        Preguntas preguntaEsperada = Preguntas.builder()
                .pregunta("hola")
                .respuesta("adios")
                .build();

        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(any())).thenReturn(resultSet);
        when(resultSet.next()).thenAnswer(new Answer<Boolean>() {

            private int counter = 0;

            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                if (counter < 1) {
                    counter++;
                    return true;
                } else {
                    return false;
                }
            }
        });
        doReturn(preguntaEsperada.getPregunta()).when(resultSet).getString(any());
        when(resultSet.getString(any())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {

                if (invocationOnMock.getArgument(0).equals("pregunta")) {
                    return preguntaEsperada.getPregunta();
                } else if (invocationOnMock.getArgument(0).equals("respuesta")) {
                    return preguntaEsperada.getRespuesta();
                } else {
                    return null;
                }
            }
        });

        List<Preguntas> listaPreguntas = preguntasManager.respuestasCon(connection, letra);

        MatcherAssert.assertThat(listaPreguntas, Matchers.hasSize(1));
        MatcherAssert.assertThat(listaPreguntas.iterator().next(), Matchers.is(preguntaEsperada));

    }

}
*/