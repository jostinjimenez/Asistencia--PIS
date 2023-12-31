package modelLuis.tablas;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import model.Asistencia;
import model.Estudiante;
import model.Matricula;
import model.catalogo.TipoFalta;
import modelLuis.controller.ControllerAsistencia;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class ModelTableAsistencia2 extends AbstractTableModel {

    private ListaEnlazada<Matricula> matriculas;
    private ControllerAsistencia a = new ControllerAsistencia();
    private ListaEnlazada<Asistencia> Asistencias;
    private ListaEnlazada<Estudiante> estudiantes;

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 2:
                return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (!isCellEditable(rowIndex, columnIndex)) {
            return;
        }
        switch (columnIndex) {
            case 2:
                if (aValue != null) {
                    try {
                        String faltaInput = aValue.toString().toUpperCase().trim();

                        switch (faltaInput) {
                            case "T":
                                Asistencia asistenciaT = Asistencias.get(rowIndex);
                                asistenciaT.setFalta(TipoFalta.ASISTIO);
                                fireTableCellUpdated(rowIndex, columnIndex);
                                break;
                            case "J":
                                Asistencia asistenciaJ = Asistencias.get(rowIndex);
                                asistenciaJ.setFalta(TipoFalta.JUSTIFICADA);
                                fireTableCellUpdated(rowIndex, columnIndex);
                                
                                break;
                            case "I":
                                Asistencia asistenciaI = Asistencias.get(rowIndex);
                                asistenciaI.setFalta(TipoFalta.INJUSTIFICADA);
                                fireTableCellUpdated(rowIndex, columnIndex);
                              
                                break;
                            default:
                                // Manejar otros casos si es necesario
                                break;
                        }
                    } catch (VacioExceptions ex) {
                        Logger.getLogger(ModelTableAsistencia2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int getRowCount() {
        return getMatriculas().getSize();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Asistencia asis = null;
        Estudiante estudiante = null;
        try {
            estudiante = getEstudiantes().get(row);
            asis = getAsistencias().get(row);
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
        if (estudiante != null) {
            switch (col) {
                case 0:
                    return estudiante.getApellido();
                case 1:
                    return estudiante.getNombre();
                case 2:
                    return asis.getFalta();
                default:
                    return null;
            }
        } else {
            return null; // O cualquier valor predeterminado que desees en caso de que estudiante sea null.
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Apellido";
            case 1:
                return "Nombre";
            case 2:
                return "Falta";
            default:
                return null;
        }
    }

    /**
     * @return the llantas
     */
    public ListaEnlazada<Matricula> getMatriculas() {
        return matriculas;
    }

    /**
     * @param matriculas the llantas to set
     */
    public void setMatriculas(ListaEnlazada<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public ListaEnlazada<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    /**
     * @return the Asistencias
     */
    public ListaEnlazada<Asistencia> getAsistencias() {
        return Asistencias;
    }

    /**
     * @param Asistencias the Asistencias to set
     */
    public void setAsistencias(ListaEnlazada<Asistencia> Asistencias) {
        this.Asistencias = Asistencias;
    }

    /**
     * @param estudiantes the estudiantes to set
     */
    public void setEstudiantes(ListaEnlazada<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    
    
    
    

}
