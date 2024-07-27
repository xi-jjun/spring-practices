class Product < ApplicationRecord
  module Enable
    OFF = 0
    ON = 1
  end

  def enable?
    enable == Enable::ON
  end

  def set_enable
    self.enable = Enable::ON
  end

  def set_disable
    self.enable = Enable::OFF
  end

  # 현재 제품이 구매가능한지에 대한 정보를 반환하는 메서드
  #
  # @return [Boolean] 구매가능 여부 true or false
  def can_purchase?
    return false unless enable?
    return false unless stock > 0

    true
  end
end
