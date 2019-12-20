import java.util.Arrays;
import java.util.List;

/**
 * @author zzy
 * @create 2019-11-27 8:45
 */
class User {
    private Long id;
    private String name;
    private Integer age;

    public User() {
    }

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

/**
 * 找出用户列表中
 * 1）偶数ID
 * 2）年龄大于24
 * 3）用户名转为大写
 * 4）根据用户名字母倒序排列
 * 5）只输出一个用户  的名字
 */
public class StreanDemo {

    public static void main(String[] args) {

        List<User> users = Arrays.asList(
                new User(1L, "lixiaolu", 23),
                new User(2L, "libingbing", 24),
                new User(3L, "fanbingbing", 25),
                new User(4L, "liuyan", 26),
                new User(5L, "fengjie", 27),
                new User(6L, "hanhong", 28)
        );

    users.stream().filter(user -> user.getId() % 2 == 0)
                .filter(user -> user.getAge() > 24)
                .map(user -> user.getName().toUpperCase())
                .sorted((o1, o2) -> o2.compareTo(o1))
                .limit(1)
                .forEach(System.out::println);


    }
}
