import dao.BD;
import dao.impl.DentistDaoH2;
import model.Dentist;
import service.DentistService;

public class Main {
    public static void main(String[] args) {

        DentistService dentistService = new DentistService(new DentistDaoH2());

        //creamos las tablas
        BD.createTables();

        //crear algunos objetos
        Dentist dentist1 = new Dentist(132, "Vanina", "Godoy");
        Dentist dentist2 = new Dentist(456, "Juan", "Perez");
        Dentist dentist3 = new Dentist(789, "Carlos", "Suarez");

        //persistir los objetos en la BD (guardarlos)
        dentistService.save(dentist1);
        dentistService.save(dentist2);
        dentistService.save(dentist3);

        //consultar un odontólogo por id
        int id = 2;
        dentistService.findById(id);

        //actualizar alguno de los atributos de un odontólogo
        String updateName = "Andrea";
        dentist1.setName(updateName);

        dentistService.update(dentist1);
        System.out.println("El nombre actualizado es: " + dentist1.getName());

        //borrar alguno de los registros de la tabla
        int idDelete = 2;
        dentistService.delete(idDelete);

        //consultar los registros restantes de la tabla
        dentistService.findAll();

    }
}
