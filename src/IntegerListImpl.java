import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private int size;
    private Integer[] storage;

    public IntegerListImpl() {
        storage = new Integer[100000];
    }

    public IntegerListImpl(int intSize) {
        storage = new Integer[intSize];
    }

    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    public Integer add(int item) {
        growNeeded();
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
    public Integer add(int index, int item) {
        growNeeded();
        validatorIndexItem(index);
        validatorItem(item);
        if (index == size) {
            storage[size++] = item;
            return item;

        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return item;

    }

    // Установить элемент
    // на определенную позицию,
    // затерев существующий.
    // Выбросить исключение,
    // если индекс больше
    // фактического количества элементов
    // или выходит за пределы массива.
    public Integer set(int index, int item) {
        validatorItem(item);
        validatorIndexItem(index);
        storage[index] = item;
        return item;
    }

    // Удаление элемента.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    public Integer remove(Integer item) {
        validatorItem(item);
        int index = indexOf(item);
        return remove(index);

    }

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    public Integer remove(int index) {
        validatorIndexItem(index);
        Integer item = storage[index];
        if (index != size)
            System.arraycopy(storage, index + 1, storage, index, size - index);
        size--;
        return item;
    }

    // Проверка на существование элемента.
    // Вернуть true/false;
    public boolean contains(int item) {
       Integer [] storageCopy = toArray();
       quickSelect(storageCopy);
    return binarySearch(storageCopy, item);
    }

    // Поиск элемента.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    public int indexOf(int item) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item))
                return i;
        }
        return -1;

    }

    // Поиск элемента с конца.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    public int lastIndexOf(int item) {
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    // Получить элемент по индексу.
    // Вернуть элемент или исключение,
    // если выходит за рамки фактического
    // количества элементов.
    public Integer get(int index) {
        validatorIndexItem(index);
        return storage[index];
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение,
    // если передан null.
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    // Вернуть фактическое количество элементов.
    public int size() {
        return size;
    }

    // Вернуть true,
    // если элементов в списке нет,
    // иначе false.
    public boolean isEmpty() {
        return size == 0;
    }

    // Удалить все элементы из списка.
    public void clear() {
        size = 0;
    }

    // Создать новый массив
    // из строк в списке
    // и вернуть его.
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    private void validatorItem(Integer item) {
        if (item == null)
            throw new NullItemException();
    }

    private void validatorSize() {
        if (size == storage.length)
            throw new SorageIsFullException();
    }

    private void validatorIndexItem(int index) {
        if (index < 0 || index > size) ;
        throw new NotFoundException();
    }

    public void quickSelect(Integer [] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] >= temp) {
                array[j] = array[j - 1];
                j--;
            }
        }

    }
    public  void quickSort (Integer [] array, int begin, int end){
        if (begin < end){
            int partitionIndex = partition (array, begin, end);
            quickSort(array, begin, partitionIndex);
            quickSort(array, partitionIndex+1, end);
        }

    }
    private static int partition(Integer [] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }
    private static void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
    public static boolean binarySearch(Integer [] arr, int element) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element == arr[mid]) {
                return true;
            }

            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
    private void grow (){
        storage = Arrays.copyOf(storage, size+size/2);
    }
    private void growNeeded (){
        if (size == storage.length)
            grow();

    }


}

