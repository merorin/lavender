package lavender.airwallex.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description:Parse the "strategy.json" and get the relation between operator and strategy class name.
 *
 * @author guobin On date 2018/8/11.
 * @version 1.0
 * @since jdk 1.8
 */
public class RpnStrategyConfig {

    private final static String CONFIG_PATH = "/strategy.json";
    private final static RpnStrategyConfig CONFIG = new RpnStrategyConfig();
    private final static Logger LOG = LoggerFactory.getLogger(RpnStrategyConfig.class);

    private Map<String, String> dictionary;

    private RpnStrategyConfig() {
        try {
            this.dictionary = this.parseData();
        } catch (IOException e) {
            LOG.error("Failed to parse strategy.json.", e);
        }
    }

    public static RpnStrategyConfig getInstance() {
        return CONFIG;
    }

    public Optional<String> getClassName(String operatorName) {
        return Optional.ofNullable(this.dictionary.get(operatorName));
    }

    public boolean isOperator(String action) {
        return this.dictionary.containsKey(action);
    }

    /**
     * parse config file and get the dictionary
     * @return dictionary
     * @throws IOException thrown if the file was not exist
     */
    private Map<String, String> parseData() throws IOException {
        final String url = this.getClass().getResource(CONFIG_PATH).getPath();
        final Stream<String> stringStream = Files.lines(Paths.get(url), Charset.defaultCharset());
        final String dataStr = stringStream
                .filter(Objects::nonNull)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining());

        return JSON.parseObject(dataStr, new TypeReference<Map<String, String>>(){});
    }


}
