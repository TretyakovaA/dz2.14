import java.util.Arrays;

public class StringListImpl implements StringList {
    private int size;
    private String[] storage;

    public StringListImpl() {
        storage = new String[10];
    }

    public StringListImpl(int intSize) {
        storage = new String[intSize];
    }

    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    public String add(String item){
        validatorSize();
        validatorItem(item);
        storage[size++] = item;
        return item;
    }

    // Добавление элемента
    // на определенную позицию списка.
    // Если выходит за пределы фактического
    // количества элементов или массива,
    // выбросить исключение.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
   public String add(int index, String item){
        validatorIndexItem(index);
        validatorSize();
        validatorItem(item);
        if (index == size) {
            storage[size++] = item;
            return item;

       }
       System.arraycopy(storage, index, storage, index+1, size - index);
        storage [index]= item;
        size++;
        return  item;

    }

    // Установить элемент
    // на определенную позицию,
    // затерев существующий.
    // Выбросить исключение,
    // если индекс больше
    // фактического количества элементов
    // или выходит за пределы массива.
    public String set(int index, String item){
        validatorItem(item);
        validatorIndexItem(index);
        storage [index] = item;
        return item;
    }

    // Удаление элемента.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    public String remove(String item){
        validatorItem(item);
        int index = indexOf(item);
        return  remove(index);

    }

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    public String remove(int index){
        validatorIndexItem(index);
        String item = storage[index];
        if (index != size)
            System.arraycopy(storage, index+1, storage, index, size - index);
        size--;
        return item;
    }

    // Проверка на существование элемента.
    // Вернуть true/false;
    public boolean contains(String item){
        return indexOf(item) == -1;
    }

    // Поиск элемента.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    public int indexOf(String item){
        for (int i = 0; i < size; i++) {
            if (storage [i].equals(item))
                return i;
        }
        return -1;

    }

    // Поиск элемента с конца.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    public int lastIndexOf(String item){
        for (int i = size-1; i >= 0; i--) {
            if (storage [i].equals(item)) {
                return  i;
            }
        } return -1;
    }

    // Получить элемент по индексу.
    // Вернуть элемент или исключение,
    // если выходит за рамки фактического
    // количества элементов.
    public String get(int index){
        validatorIndexItem(index);
        return storage [index];
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение,
    // если передан null.
    public boolean equals(StringList otherList){
       return Arrays.equals(this.toArray(), otherList.toArray());
    }

    // Вернуть фактическое количество элементов.
    public int size(){
        return size;
    }

    // Вернуть true,
    // если элементов в списке нет,
    // иначе false.
    public boolean isEmpty(){
        return size == 0;
    }

    // Удалить все элементы из списка.
    public void clear(){
        size = 0;
    }

    // Создать новый массив
    // из строк в списке
    // и вернуть его.
    public String [] toArray(){
        return Arrays.copyOf(storage, size);
    }

    private void validatorItem(String item) {
        if (item == null)
            throw new NullItemException();
    }
    private  void  validatorSize (){
        if (size == storage.length)
        throw new SorageIsFullException();
    }
    private void validatorIndexItem (int index){
        if (index <0 || index > size);
        throw new NotFoundException();
    }


}
